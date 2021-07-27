package com.example.vehicles.api.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
import java.util.Arrays;

@ApiModel("VehicleUpdateRequest")
public class VehicleUpdateRequestDTO {

    @ApiModelProperty("Vehicle identifier")
    @NotBlank
    private String id;

    @ApiModelProperty("Vehicle mark name")
    @NotBlank
    @Size(min = 3, max = 255)
    private String markName;

    @ApiModelProperty("Vehicle model")
    @NotBlank
    @Size(min = 3, max = 255)
    private String model;

    @ApiModelProperty("Vehicle fabrication year")
    @NotNull
    @Min(1900)
    @Max(2022)
    private Integer fabricationYear;

    @ApiModelProperty("Vehicle image")
    private String image;

    @ApiModelProperty("Vehicle color")
    private String color;

    @ApiModelProperty("Vehicle mileage")
    private Integer mileage;

    public String getId() {
        return id;
    }

    public VehicleUpdateRequestDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getMarkName() {
        return markName;
    }

    public VehicleUpdateRequestDTO setMarkName(String markName) {
        this.markName = markName;
        return this;
    }

    public String getModel() {
        return model;
    }

    public VehicleUpdateRequestDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getFabricationYear() {
        return fabricationYear;
    }

    public VehicleUpdateRequestDTO setFabricationYear(Integer fabricationYear) {
        this.fabricationYear = fabricationYear;
        return this;
    }

    public String getImage() {
        return image;
    }

    public VehicleUpdateRequestDTO setImage(String image) {
        this.image = image;
        return this;
    }

    public String getColor() {
        return color;
    }

    public VehicleUpdateRequestDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public VehicleUpdateRequestDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    @Override
    public String toString() {
        return "VehicleUpdateRequestDTO{" +
                "id='" + id + '\'' +
                ", markName='" + markName + '\'' +
                ", model='" + model + '\'' +
                ", fabricationYear=" + fabricationYear +
                ", image='" + image + '\'' +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
