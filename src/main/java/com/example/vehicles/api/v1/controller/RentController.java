package com.example.vehicles.api.v1.controller;

import com.example.vehicles.api.v1.controller.filter.RentFilter;
import com.example.vehicles.api.v1.dto.RentRequestDTO;
import com.example.vehicles.api.v1.dto.RentResponseDTO;
import com.example.vehicles.api.v1.service.RentService;
import com.example.vehicles.api.v1.service.exception.AbstractException;
import com.example.vehicles.api.v1.service.vo.RentVO;
import com.example.vehicles.mapper.RentMapper;
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


@RestController("RentControllerV1")
@RequestMapping(PATH_V1 + "/rents")
public class RentController {

    @Autowired
    private RentMapper mapper;

    @Autowired
    private RentService service;

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a rent")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentResponseDTO> rent(
            @ApiParam(value = "Rent details", required = true)
            @Valid @RequestBody RentRequestDTO requestDTO) throws AbstractException {
        RentVO responseVO = service.rent(requestDTO);
        return ResponseEntity.ok(mapper.toRentResponseDTO(responseVO));
    }

    @ApiOperation("Search rents")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<RentResponseDTO>> find(RentFilter rentFilter, Pageable pageable) throws AbstractException {
        Page<RentVO> responseVO = service.search(rentFilter, pageable);
        return ResponseEntity.ok(mapper.toRentsResponseDTOPage(responseVO));
    }

    @ApiOperation("Find a rent by id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentResponseDTO> findById(
            @ApiParam(value = "Rent identifier", required = true)
            @PathVariable String id) throws AbstractException {
        RentVO responseVO = service.findById(id);
        return ResponseEntity.ok(mapper.map(responseVO, RentResponseDTO.class));
    }

    @ApiOperation("Delete a rent by id")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(
            @ApiParam(value = "Rent identifier", required = true)
            @PathVariable String id) throws AbstractException {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
