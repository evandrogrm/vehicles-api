package com.example.vehicles.api.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel("MarkUpdateRequest")
public class MarkUpdateRequestDTO {

    @ApiModelProperty("Mark identifier")
    @NotBlank
    private String id;

    @ApiModelProperty("Mark name")
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    public String getId() {
        return id;
    }

    public MarkUpdateRequestDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MarkUpdateRequestDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "MarkUpdateRequestDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
