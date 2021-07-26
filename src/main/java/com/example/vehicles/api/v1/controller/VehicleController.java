package com.example.vehicles.api.v1.controller;

import com.example.vehicles.api.v1.controller.filter.VehicleFilter;
import com.example.vehicles.api.v1.dto.VehicleRequestDTO;
import com.example.vehicles.api.v1.dto.VehicleResponseDTO;
import com.example.vehicles.api.v1.dto.VehicleUpdateRequestDTO;
import com.example.vehicles.api.v1.service.VehicleService;
import com.example.vehicles.api.v1.service.exception.AbstractException;
import com.example.vehicles.api.v1.service.vo.VehicleVO;
import com.example.vehicles.mapper.VehicleMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.vehicles.api.VersionPath.PATH_V1;

@RestController("VehicleControllerV1")
@RequestMapping(PATH_V1 + "/vehicles")
public class VehicleController {

    @Autowired
    private VehicleMapper mapper;

    @Autowired
    private VehicleService service;

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a vehicle")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleResponseDTO> create(
            @ApiParam(value = "Vehicle details", required = true)
            @Valid @RequestBody VehicleRequestDTO requestDTO) throws AbstractException {
        VehicleVO requestVO = mapper.map(requestDTO, VehicleVO.class);
        VehicleVO responseVO = service.create(requestVO);
        VehicleResponseDTO responseDTO = mapper.toVehicleResponseDTO(responseVO);
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation("Updates a vehicle")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleResponseDTO> update(
            @ApiParam(value = "Vehicle details", required = true)
            @Valid @RequestBody VehicleUpdateRequestDTO requestDTO) throws AbstractException {
        VehicleVO requestVO = mapper.map(requestDTO, VehicleVO.class);
        VehicleVO responseVO = service.update(requestVO);
        VehicleResponseDTO responseDTO = mapper.toVehicleResponseDTO(responseVO);
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation("Search vehicles")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<VehicleResponseDTO>> find(VehicleFilter vehicleFilter, Pageable pageable) {
        Page<VehicleVO> responseVO = service.search(vehicleFilter, pageable);
        return ResponseEntity.ok(mapper.toVehiclesResponseDTOPage(responseVO));
    }

    @ApiOperation("Find a vehicle by id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleResponseDTO> findById(
            @ApiParam(value = "Vehicle identifier", required = true)
            @PathVariable String id) throws AbstractException {
        VehicleVO responseVO = service.findById(id);
        VehicleResponseDTO responseDTO = mapper.map(responseVO, VehicleResponseDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation("Delete a vehicle by id")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(
            @ApiParam(value = "Vehicle identifier", required = true)
            @PathVariable String id) throws AbstractException {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
