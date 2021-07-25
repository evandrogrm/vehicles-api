package com.example.vehicles.api.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel("MarkRequest")
public class MarkRequestDTO {

    @ApiModelProperty("Mark name")
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    public String getName() {
        return name;
    }

    public MarkRequestDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "MarkRequestDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
