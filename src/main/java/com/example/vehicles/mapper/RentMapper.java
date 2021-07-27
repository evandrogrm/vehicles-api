package com.example.vehicles.mapper;

import com.example.vehicles.api.v1.dto.RentResponseDTO;
import com.example.vehicles.api.v1.dto.UserResponseDTO;
import com.example.vehicles.api.v1.dto.VehicleResponseDTO;
import com.example.vehicles.api.v1.service.vo.RentVO;
import com.example.vehicles.api.v1.service.vo.UserVO;
import com.example.vehicles.api.v1.service.vo.VehicleVO;
import com.example.vehicles.domain.Rent;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public final class RentMapper extends Mapper {

    public Page<RentResponseDTO> toRentsResponseDTOPage(Page<RentVO> responseVO) {
        return responseVO.map(vo -> map(vo, RentResponseDTO.class));
    }

    public Page<RentVO> toRentResponseVOPage(Page<Rent> rents) {
        return rents.map(v -> toRentVO(v));
    }

    public RentVO toRentVO(Rent rent) {
        VehicleVO vehicleVO = map(rent.getVehicle(), VehicleVO.class);

        if (ArrayUtils.isNotEmpty(rent.getVehicle().getImage())) {
            vehicleVO.setImage(new String(rent.getVehicle().getImage()));
        }

        UserVO userVO = map(rent.getUser(), UserVO.class);
        return map(rent, RentVO.class)
                .setVehicle(vehicleVO)
                .setUser(userVO);
    }

    public RentResponseDTO toRentResponseDTO(RentVO responseVO) {
        VehicleResponseDTO vehicleDTO = map(responseVO.getVehicle(), VehicleResponseDTO.class);
        UserResponseDTO userDTO = map(responseVO.getUser(), UserResponseDTO.class);
        return map(responseVO, RentResponseDTO.class)
                .setVehicle(vehicleDTO)
                .setUser(userDTO);
    }
}
