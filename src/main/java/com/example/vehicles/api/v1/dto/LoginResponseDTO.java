package com.example.vehicles.api.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("LoginResponse")
public class LoginResponseDTO {

    @ApiModelProperty("User identifier")
    private String id;

    @ApiModelProperty("User name")
    private String name;

    @ApiModelProperty("User email")
    private String email;

    @ApiModelProperty("User token")
    private String token;

    public String getId() {
        return id;
    }

    public LoginResponseDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LoginResponseDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoginResponseDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getToken() {
        return token;
    }

    public LoginResponseDTO setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
