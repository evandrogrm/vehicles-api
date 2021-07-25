package com.example.vehicles.mapper;

import com.example.vehicles.api.v1.dto.MarkResponseDTO;
import com.example.vehicles.api.v1.dto.VehicleResponseDTO;
import com.example.vehicles.api.v1.service.vo.MarkVO;
import com.example.vehicles.api.v1.service.vo.VehicleVO;
import com.example.vehicles.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public final class VehicleMapper extends Mapper {

    @Autowired
    private MarkMapper markMapper;

    public Page<VehicleResponseDTO> toVehiclesResponseDTOPage(Page<VehicleVO> responseVO) {
        return responseVO.map(vo -> map(vo, VehicleResponseDTO.class));
    }

    public Page<VehicleVO> toVehicleResponseVOPage(Page<Vehicle> vehicles) {
        return vehicles.map(v -> map(v, VehicleVO.class));
    }

    public VehicleVO toVehicleVO(Vehicle vehicle) {
        MarkVO markVO = markMapper.toMarkVO(vehicle.getMark());
        return map(vehicle, VehicleVO.class)
                .setMark(markVO);
    }

    public VehicleResponseDTO toVehicleResponseDTO(VehicleVO responseVO) {
        MarkResponseDTO markDTO = markMapper.toMarkResponseDTO(responseVO.getMark());
        return map(responseVO, VehicleResponseDTO.class)
                .setMark(markDTO);
    }
}
