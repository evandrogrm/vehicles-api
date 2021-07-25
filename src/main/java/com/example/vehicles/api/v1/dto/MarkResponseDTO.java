package com.example.vehicles.api.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("MarkResponse")
public class MarkResponseDTO {

    @ApiModelProperty("Mark identifier")
    private String id;

    @ApiModelProperty("Mark name")
    private String name;

    public String getId() {
        return id;
    }

    public MarkResponseDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MarkResponseDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "MarkResponseDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
