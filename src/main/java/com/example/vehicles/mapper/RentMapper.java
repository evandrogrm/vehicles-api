package com.example.vehicles.mapper;

import com.example.vehicles.api.v1.dto.MarkResponseDTO;
import com.example.vehicles.api.v1.dto.RentResponseDTO;
import com.example.vehicles.api.v1.dto.UserResponseDTO;
import com.example.vehicles.api.v1.dto.VehicleResponseDTO;
import com.example.vehicles.api.v1.service.vo.MarkVO;
import com.example.vehicles.api.v1.service.vo.RentVO;
import com.example.vehicles.api.v1.service.vo.UserVO;
import com.example.vehicles.api.v1.service.vo.VehicleVO;
import com.example.vehicles.domain.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public final class RentMapper extends Mapper {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private UserMapper userMapper;

    public Page<RentResponseDTO> toRentsResponseDTOPage(Page<RentVO> responseVO) {
        return responseVO.map(vo -> map(vo, RentResponseDTO.class));
    }

    public Page<RentVO> toRentResponseVOPage(Page<Rent> rents) {
        return rents.map(v -> map(v, RentVO.class));
    }

    public RentVO toRentVO(Rent rent) {
        VehicleVO vehicleVO = vehicleMapper.toVehicleVO(rent.getVehicle());
        UserVO userVO = userMapper.toUserVO(rent.getUser());
        return map(rent, RentVO.class)
                .setVehicle(vehicleVO)
                .setUser(userVO);
    }

    public RentResponseDTO toRentResponseDTO(RentVO responseVO) {
        VehicleResponseDTO vehicleDTO = vehicleMapper.toVehicleResponseDTO(responseVO.getVehicle());
        UserResponseDTO userDTO = userMapper.toUserDTO(responseVO.getUser());
        return map(responseVO, RentResponseDTO.class)
                .setVehicle(vehicleDTO)
                .setUser(userDTO);
    }
}
