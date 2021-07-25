package com.example.vehicles.api.v1.service.vo;

import java.util.Arrays;
import java.util.Date;

public class VehicleVO {

    private String id;
    private String markId;
    private MarkVO mark;
    private String markName;
    private String model;
    private Integer fabricationYear;
    private byte[] image;
    private String color;
    private Integer mileage;
    private Date createdAt;
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public VehicleVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getMarkId() {
        return markId;
    }

    public VehicleVO setMarkId(String markId) {
        this.markId = markId;
        return this;
    }

    public MarkVO getMark() {
        return mark;
    }

    public VehicleVO setMark(MarkVO mark) {
        this.mark = mark;
        return this;
    }

    public String getMarkName() {
        return markName;
    }

    public VehicleVO setMarkName(String markName) {
        this.markName = markName;
        return this;
    }

    public String getModel() {
        return model;
    }

    public VehicleVO setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getFabricationYear() {
        return fabricationYear;
    }

    public VehicleVO setFabricationYear(Integer fabricationYear) {
        this.fabricationYear = fabricationYear;
        return this;
    }

    public byte[] getImage() {
        return image;
    }

    public VehicleVO setImage(byte[] image) {
        this.image = image;
        return this;
    }

    public String getColor() {
        return color;
    }

    public VehicleVO setColor(String color) {
        this.color = color;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public VehicleVO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public VehicleVO setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public VehicleVO setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        return "VehicleVO{" +
                "id='" + id + '\'' +
                ", markId='" + markId + '\'' +
                ", mark=" + mark +
                ", markName='" + markName + '\'' +
                ", model='" + model + '\'' +
                ", fabricationYear=" + fabricationYear +
                ", image=" + Arrays.toString(image) +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
