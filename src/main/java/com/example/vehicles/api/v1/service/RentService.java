package com.example.vehicles.api.v1.service;

import com.example.vehicles.api.v1.controller.filter.RentFilter;
import com.example.vehicles.api.v1.dto.RentRequestDTO;
import com.example.vehicles.api.v1.repository.RentRepositoryV1;
import com.example.vehicles.api.v1.repository.UserRepositoryV1;
import com.example.vehicles.api.v1.repository.VehicleRepositoryV1;
import com.example.vehicles.api.v1.service.exception.*;
import com.example.vehicles.api.v1.service.vo.RentVO;
import com.example.vehicles.domain.Rent;
import com.example.vehicles.domain.User;
import com.example.vehicles.domain.Vehicle;
import com.example.vehicles.mapper.RentMapper;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Date;
import java.util.UUID;

import static com.example.vehicles.api.v1.service.LoggedUserService.getLoggedUser;

@Service
public class RentService {

    @Autowired
    private RentRepositoryV1 repository;

    @Autowired
    private RentMapper mapper;

    @Autowired
    private VehicleRepositoryV1 vehicleRepositoryV1;

    @Autowired
    private UserRepositoryV1 userRepositoryV1;

    @Value("${MAXIMUM_DAYS_DIFFERENCE:30}")
    private int maximumDaysDifference = 30;

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public RentVO rent(RentRequestDTO requestDTO) throws AbstractException {
        Vehicle vehicle = vehicleRepositoryV1.findById(requestDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException(requestDTO.getVehicleId()));

        User user;
        if (requestDTO.getUserId() != null) {
            user = userRepositoryV1.findById(requestDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(requestDTO.getUserId()));
        } else {
            user = getLoggedUser();
        }

        validateNewRent(user.getId(), vehicle.getId(), requestDTO.getRentStartAt(), requestDTO.getRentEndAt());

        Rent rent = new Rent()
                .setId(UUID.randomUUID().toString())
                .setVehicleId(vehicle.getId())
                .setVehicle(vehicle)
                .setUserId(user.getId())
                .setUser(user)
                .setRentStartAt(requestDTO.getRentStartAt())
                .setRentEndAt(requestDTO.getRentEndAt());

        rent = repository.save(rent);

        return mapper.toRentVO(rent);
    }

    private void validateNewRent(String userId, String vehicleId, Date rentStartAt, Date rentEndAt) throws AbstractException {
        // data inicial maior que a atual
        if (rentStartAt.before(new Date())) {
            throw new RentStartDateBeforeNowException(rentStartAt);
        }

        // data final maior ou igual a inicial
        if (rentEndAt.before(rentStartAt)) {
            throw new RentEndBeforeStartException(rentEndAt);
        }

        // periodo maximo de 30 dias
        validateMaximumDifferenceDate(rentStartAt, rentEndAt);

        // veiculo para um unico usuario na data
        validateVehicleAlreadyRent(vehicleId, rentStartAt, rentEndAt);

        // usuario pode possuir apenas um veiculo na data
        validateUserAlreadyRentOnOtherVehicle(userId, rentStartAt, rentEndAt);
    }

    private void validateMaximumDifferenceDate(Date rentStartAt, Date rentEndAt) throws RentDaysDifferenceTooLongException {
        DateTime startDateTime = new DateTime(rentStartAt);
        DateTime endDateTime = new DateTime(rentEndAt);
        int daysBetween = Days.daysBetween(startDateTime, endDateTime).getDays();
        if (daysBetween > maximumDaysDifference) {
            throw new RentDaysDifferenceTooLongException(daysBetween);
        }
    }

    private void validateVehicleAlreadyRent(String vehicleId, Date rentStartAt, Date rentEndAt) throws VehicleAlreadyRentException {
        Rent rentForOtherUser = repository.findByVehicleBetweenDates(vehicleId, rentStartAt, rentEndAt);
        if (rentForOtherUser != null) {
            throw new VehicleAlreadyRentException(rentForOtherUser.getId(), rentForOtherUser.getVehicleId());
        }
    }

    private void validateUserAlreadyRentOnOtherVehicle(String userId, Date rentStartAt, Date rentEndAt) throws UserAlreadyRentException {
        Rent rentAlreadyOnOtherVehicle = repository.findByUserBetweenDates(userId, rentStartAt, rentEndAt);
        if (rentAlreadyOnOtherVehicle != null) {
            throw new UserAlreadyRentException(rentAlreadyOnOtherVehicle.getId(), rentAlreadyOnOtherVehicle.getUserId());
        }
    }

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Page<RentVO> search(RentFilter rentFilter, Pageable pageable) {
        Page<Rent> rents = repository.findAll(rentFilter, pageable);
        rents.map(rent -> {
            rent.setVehicle(vehicleRepositoryV1.findById(rent.getVehicleId()).get());
            rent.setUser(userRepositoryV1.findById(rent.getUserId()).get());
            return rent;
        });
        return mapper.toRentResponseVOPage(rents);
    }

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public RentVO findById(String id) throws AbstractException {
        Rent rent = repository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));

        rent.setVehicle(vehicleRepositoryV1.findById(rent.getVehicleId()).get());
        rent.setUser(userRepositoryV1.findById(rent.getUserId()).get());

        return mapper.toRentVO(rent);
    }

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public void deleteById(String id) throws AbstractException {
        Rent rent = repository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
        repository.delete(rent);
    }
}
