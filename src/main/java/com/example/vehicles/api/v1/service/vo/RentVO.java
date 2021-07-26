package com.example.vehicles.api.v1.service.vo;

import java.util.Date;

public class RentVO {

    private String id;
    private VehicleVO vehicle;
    private UserVO user;
    private Date rentStartAt;
    private Date rentEndAt;
    private Date createdAt;
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public RentVO setId(String id) {
        this.id = id;
        return this;
    }

    public VehicleVO getVehicle() {
        return vehicle;
    }

    public RentVO setVehicle(VehicleVO vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public UserVO getUser() {
        return user;
    }

    public RentVO setUser(UserVO user) {
        this.user = user;
        return this;
    }

    public Date getRentStartAt() {
        return rentStartAt;
    }

    public RentVO setRentStartAt(Date rentStartAt) {
        this.rentStartAt = rentStartAt;
        return this;
    }

    public Date getRentEndAt() {
        return rentEndAt;
    }

    public RentVO setRentEndAt(Date rentEndAt) {
        this.rentEndAt = rentEndAt;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public RentVO setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public RentVO setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        return "RentVO{" +
                "id='" + id + '\'' +
                ", vehicle=" + vehicle +
                ", user=" + user +
                ", rentStartAt=" + rentStartAt +
                ", rentEndAt=" + rentEndAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
