package com.example.vehicles.api.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel("UserRequest")
public class UserRequestDTO {

    @ApiModelProperty("User name")
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @ApiModelProperty("User email")
    @NotBlank
    @Size(min = 3, max = 255)
    private String email;

    public String getName() {
        return name;
    }

    public UserRequestDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRequestDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
