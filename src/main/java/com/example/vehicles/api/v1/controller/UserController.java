package com.example.vehicles.api.v1.controller;

import com.example.vehicles.api.v1.controller.filter.UserFilter;
import com.example.vehicles.api.v1.dto.UserRequestDTO;
import com.example.vehicles.api.v1.dto.UserResponseDTO;
import com.example.vehicles.api.v1.service.UserService;
import com.example.vehicles.api.v1.service.exception.AbstractException;
import com.example.vehicles.api.v1.service.vo.UserVO;
import com.example.vehicles.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.vehicles.api.VersionPath.PATH_V1;

@RestController("UserControllerV1")
@RequestMapping(PATH_V1 + "/users")
public class UserController {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserService service;

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates an user")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> create(
            @ApiParam(value = "User details", required = true)
            @RequestBody UserRequestDTO requestDTO) throws AbstractException {
        UserVO requestVO = mapper.map(requestDTO, UserVO.class);
        UserVO responseVO = service.create(requestVO);
        UserResponseDTO responseDTO = mapper.map(responseVO, UserResponseDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation("Search users")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UserResponseDTO>> find(UserFilter userFilter, Pageable pageable) {
        Page<UserVO> responseVO = service.search(userFilter, pageable);
        return ResponseEntity.ok(mapper.toUsersResponseDTOPage(responseVO));
    }

    @ApiOperation("Find an user by id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> findById(
            @ApiParam(value = "User identifier", required = true)
            @PathVariable String id) throws AbstractException {
        UserVO responseVO = service.findById(id);
        UserResponseDTO responseDTO = mapper.map(responseVO, UserResponseDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation("Delete an user by id")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(
            @ApiParam(value = "User identifier", required = true)
            @PathVariable String id) throws AbstractException {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
