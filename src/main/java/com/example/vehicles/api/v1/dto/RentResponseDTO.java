package com.example.vehicles.api.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("RentResponse")
public class RentResponseDTO {

    @ApiModelProperty("Rent identifier")
    private String id;

    @ApiModelProperty("Rent vehicle")
    private VehicleResponseDTO vehicle;

    @ApiModelProperty("Rent user")
    private UserResponseDTO user;

    @ApiModelProperty("Rent start date")
    private Date rentStartAt;

    @ApiModelProperty("Rent end date")
    private Date rentEndAt;

    public String getId() {
        return id;
    }

    public RentResponseDTO setId(String id) {
        this.id = id;
        return this;
    }

    public VehicleResponseDTO getVehicle() {
        return vehicle;
    }

    public RentResponseDTO setVehicle(VehicleResponseDTO vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public RentResponseDTO setUser(UserResponseDTO user) {
        this.user = user;
        return this;
    }

    public Date getRentStartAt() {
        return rentStartAt;
    }

    public RentResponseDTO setRentStartAt(Date rentStartAt) {
        this.rentStartAt = rentStartAt;
        return this;
    }

    public Date getRentEndAt() {
        return rentEndAt;
    }

    public RentResponseDTO setRentEndAt(Date rentEndAt) {
        this.rentEndAt = rentEndAt;
        return this;
    }

    @Override
    public String toString() {
        return "RentResponseDTO{" +
                "id='" + id + '\'' +
                ", vehicle=" + vehicle +
                ", user=" + user +
                ", rentStartAt=" + rentStartAt +
                ", rentEndAt=" + rentEndAt +
                '}';
    }
}
