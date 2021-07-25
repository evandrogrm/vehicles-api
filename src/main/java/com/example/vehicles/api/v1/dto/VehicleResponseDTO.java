package com.example.vehicles.api.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;

@ApiModel("VehicleResponse")
public class VehicleResponseDTO {

    @ApiModelProperty("Vehicle identifier")
    private String id;

    @ApiModelProperty("Vehicle mark")
    private MarkResponseDTO mark;

    @ApiModelProperty("Vehicle model")
    private String model;

    @ApiModelProperty("Vehicle fabrication year")
    private Integer fabricationYear;

    @ApiModelProperty("Vehicle image")
    private byte[] image;

    @ApiModelProperty("Vehicle color")
    private String color;

    @ApiModelProperty("Vehicle mileage")
    private Integer mileage;

    public String getId() {
        return id;
    }

    public VehicleResponseDTO setId(String id) {
        this.id = id;
        return this;
    }

    public MarkResponseDTO getMark() {
        return mark;
    }

    public VehicleResponseDTO setMark(MarkResponseDTO mark) {
        this.mark = mark;
        return this;
    }

    public String getModel() {
        return model;
    }

    public VehicleResponseDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getFabricationYear() {
        return fabricationYear;
    }

    public VehicleResponseDTO setFabricationYear(Integer fabricationYear) {
        this.fabricationYear = fabricationYear;
        return this;
    }

    public byte[] getImage() {
        return image;
    }

    public VehicleResponseDTO setImage(byte[] image) {
        this.image = image;
        return this;
    }

    public String getColor() {
        return color;
    }

    public VehicleResponseDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public VehicleResponseDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    @Override
    public String toString() {
        return "VehicleResponseDTO{" +
                "id='" + id + '\'' +
                ", mark=" + mark +
                ", model='" + model + '\'' +
                ", fabricationYear=" + fabricationYear +
                ", image=" + Arrays.toString(image) +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
