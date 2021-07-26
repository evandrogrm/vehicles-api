package com.example.vehicles.api.v1.service;

import com.example.vehicles.api.v1.controller.filter.VehicleFilter;
import com.example.vehicles.api.v1.repository.MarkRepositoryV1;
import com.example.vehicles.api.v1.repository.VehicleRepositoryV1;
import com.example.vehicles.api.v1.service.exception.AbstractException;
import com.example.vehicles.api.v1.service.exception.VehicleNotFoundException;
import com.example.vehicles.api.v1.service.vo.VehicleVO;
import com.example.vehicles.domain.Mark;
import com.example.vehicles.domain.Vehicle;
import com.example.vehicles.mapper.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepositoryV1 repository;

    @Autowired
    private VehicleMapper mapper;

    @Autowired
    private MarkService markService;

    @Autowired
    private MarkRepositoryV1 markRepositoryV1;

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public VehicleVO create(VehicleVO requestVO) throws AbstractException {
        Mark mark = markService.getOrCreateByName(requestVO.getMarkName());

        Vehicle vehicle = mapper.map(requestVO, Vehicle.class)
                .setId(UUID.randomUUID().toString())
                .setMarkId(mark.getId())
                .setMark(mark);

        vehicle = repository.save(vehicle);

        return mapper.toVehicleVO(vehicle);
    }

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public VehicleVO update(VehicleVO requestVO) throws AbstractException {
        Mark mark = markService.getOrCreateByName(requestVO.getMarkName());

        Vehicle vehicle = findEntityById(requestVO.getId())
                .setMarkId(mark.getId())
                .setMark(mark)
                .setModel(requestVO.getModel())
                .setFabricationYear(requestVO.getFabricationYear())
                .setImage(requestVO.getImage())
                .setColor(requestVO.getColor())
                .setMileage(requestVO.getMileage());

        vehicle = repository.save(vehicle);

        return mapper.toVehicleVO(vehicle);
    }

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Page<VehicleVO> search(VehicleFilter vehicleFilter, Pageable pageable) {
        Page<Vehicle> vehicles = repository.findAll(vehicleFilter, pageable);
        vehicles.map(v -> v.setMark(markRepositoryV1.findById(v.getMarkId()).get()));
        return mapper.toVehicleResponseVOPage(vehicles);
    }

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public VehicleVO findById(String id) throws AbstractException {
        Vehicle vehicle = findEntityById(id);
        return mapper.map(vehicle, VehicleVO.class);
    }

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Vehicle findEntityById(String id) throws AbstractException {
        Vehicle vehicle = repository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));

        vehicle.setMark(markRepositoryV1.findById(vehicle.getMarkId()).get());

        return vehicle;
    }

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public void deleteById(String id) throws AbstractException {
        Vehicle vehicle = repository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
        repository.delete(vehicle);
    }
}
