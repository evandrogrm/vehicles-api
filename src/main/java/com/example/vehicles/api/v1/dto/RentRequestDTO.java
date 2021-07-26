package com.example.vehicles.api.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("RentRequest")
public class RentRequestDTO {

    @ApiModelProperty("Vehicle identifier")
    @NotBlank
    private String vehicleId;

    @ApiModelProperty("User identifier")
    private String userId;

    @ApiModelProperty("Rent start date")
    @NotNull
    private Date rentStartAt;

    @ApiModelProperty("Rent end date")
    @NotNull
    private Date rentEndAt;

    public String getVehicleId() {
        return vehicleId;
    }

    public RentRequestDTO setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public RentRequestDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Date getRentStartAt() {
        return rentStartAt;
    }

    public RentRequestDTO setRentStartAt(Date rentStartAt) {
        this.rentStartAt = rentStartAt;
        return this;
    }

    public Date getRentEndAt() {
        return rentEndAt;
    }

    public RentRequestDTO setRentEndAt(Date rentEndAt) {
        this.rentEndAt = rentEndAt;
        return this;
    }

    @Override
    public String toString() {
        return "RentRequestDTO{" +
                "vehicleId='" + vehicleId + '\'' +
                ", userId='" + userId + '\'' +
                ", rentStartAt=" + rentStartAt +
                ", rentEndAt=" + rentEndAt +
                '}';
    }
}
