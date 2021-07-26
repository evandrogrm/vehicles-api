package com.example.vehicles.api.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel("UserUpdateRequest")
public class UserUpdateRequestDTO {

    @ApiModelProperty("User identifier")
    @NotBlank
    private String id;

    @ApiModelProperty("User name")
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @ApiModelProperty("User email")
    @NotBlank
    @Size(min = 3, max = 255)
    private String email;

    public String getId() {
        return id;
    }

    public UserUpdateRequestDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserUpdateRequestDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserUpdateRequestDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "UserUpdateRequestDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
