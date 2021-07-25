package com.example.vehicles.api.v1.controller;

import com.example.vehicles.api.v1.controller.filter.MarkFilter;
import com.example.vehicles.api.v1.dto.MarkRequestDTO;
import com.example.vehicles.api.v1.dto.MarkResponseDTO;
import com.example.vehicles.api.v1.dto.MarkRequestDTO;
import com.example.vehicles.api.v1.dto.MarkResponseDTO;
import com.example.vehicles.api.v1.service.MarkService;
import com.example.vehicles.api.v1.service.MarkService;
import com.example.vehicles.api.v1.service.exception.AbstractException;
import com.example.vehicles.api.v1.service.vo.MarkVO;
import com.example.vehicles.api.v1.service.vo.MarkVO;
import com.example.vehicles.mapper.MarkMapper;
import com.example.vehicles.mapper.MarkMapper;
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

@RestController("MarkControllerV1")
@RequestMapping(PATH_V1 + "/marks")
public class MarkController {

    @Autowired
    private MarkMapper mapper;

    @Autowired
    private MarkService service;

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a mark")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarkResponseDTO> create(
            @ApiParam(value = "Mark details", required = true)
            @Valid @RequestBody MarkRequestDTO requestDTO) throws AbstractException {
        MarkVO requestVO = mapper.map(requestDTO, MarkVO.class);
        MarkVO responseVO = service.create(requestVO);
        MarkResponseDTO responseDTO = mapper.map(responseVO, MarkResponseDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation("Search marks")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<MarkResponseDTO>> find(MarkFilter markFilter, Pageable pageable) {
        Page<MarkVO> responseVO = service.search(markFilter, pageable);
        return ResponseEntity.ok(mapper.toMarksResponseDTOPage(responseVO));
    }

    @ApiOperation("Find a mark by id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarkResponseDTO> findById(
            @ApiParam(value = "Mark identifier", required = true)
            @PathVariable String id) throws AbstractException {
        MarkVO responseVO = service.findById(id);
        MarkResponseDTO responseDTO = mapper.map(responseVO, MarkResponseDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation("Delete a mark by id")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(
            @ApiParam(value = "Mark identifier", required = true)
            @PathVariable String id) throws AbstractException {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
