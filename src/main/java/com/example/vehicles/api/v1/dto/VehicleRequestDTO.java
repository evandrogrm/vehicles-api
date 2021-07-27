package com.example.vehicles.api.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
import java.util.Arrays;

@ApiModel("VehicleRequest")
public class VehicleRequestDTO {

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

    public String getMarkName() {
        return markName;
    }

    public VehicleRequestDTO setMarkName(String markName) {
        this.markName = markName;
        return this;
    }

    public String getModel() {
        return model;
    }

    public VehicleRequestDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getFabricationYear() {
        return fabricationYear;
    }

    public VehicleRequestDTO setFabricationYear(Integer fabricationYear) {
        this.fabricationYear = fabricationYear;
        return this;
    }

    public String getImage() {
        return image;
    }

    public VehicleRequestDTO setImage(String image) {
        this.image = image;
        return this;
    }

    public String getColor() {
        return color;
    }

    public VehicleRequestDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public VehicleRequestDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    @Override
    public String toString() {
        return "VehicleRequestDTO{" +
                "markName='" + markName + '\'' +
                ", model='" + model + '\'' +
                ", fabricationYear=" + fabricationYear +
                ", image='" + image + '\'' +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
