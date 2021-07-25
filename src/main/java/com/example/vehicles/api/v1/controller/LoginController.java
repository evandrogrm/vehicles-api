package com.example.vehicles.api.v1.controller;

import com.example.vehicles.api.v1.dto.LoginRequestDTO;
import com.example.vehicles.api.v1.dto.LoginResponseDTO;
import com.example.vehicles.api.v1.service.LoginService;
import com.example.vehicles.api.v1.service.exception.AbstractException;
import com.example.vehicles.api.v1.service.vo.LoginVO;
import com.example.vehicles.mapper.LoginMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.vehicles.api.VersionPath.PATH_V1;

@RestController("LoginControllerV1")
@RequestMapping(PATH_V1 + "/login")
public class LoginController {

    @Autowired
    private LoginService service;

    @Autowired
    private LoginMapper mapper;

    @ApiOperation("Login an user")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> login(
            @ApiParam(value = "User login details", required = true)
            @RequestBody LoginRequestDTO requestDTO) throws AbstractException {
        LoginVO loginVO = service.login(requestDTO);
        return ResponseEntity.ok(mapper.map(loginVO, LoginResponseDTO.class));
    }
}
