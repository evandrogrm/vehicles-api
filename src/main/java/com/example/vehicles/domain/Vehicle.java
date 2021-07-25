package com.example.vehicles.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "vehicles")
@EntityListeners(AuditingEntityListener.class)
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Column(name = "mark_id", length = 36, nullable = false)
    private String markId;

    @JoinColumn(name = "mark_id", referencedColumnName = "id", insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_vehicle_mark_id"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Mark mark;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "fabrication_year")
    private Integer fabricationYear;

    @Lob
    @Column(name = "image", columnDefinition = "mediumblob")
    private byte[] image;

    @Column(name = "color")
    private String color;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public Vehicle setId(String id) {
        this.id = id;
        return this;
    }

    public String getMarkId() {
        return markId;
    }

    public Vehicle setMarkId(String markId) {
        this.markId = markId;
        return this;
    }

    public Mark getMark() {
        return mark;
    }

    public Vehicle setMark(Mark mark) {
        this.mark = mark;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Vehicle setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getFabricationYear() {
        return fabricationYear;
    }

    public Vehicle setFabricationYear(Integer fabricationYear) {
        this.fabricationYear = fabricationYear;
        return this;
    }

    public byte[] getImage() {
        return image;
    }

    public Vehicle setImage(byte[] image) {
        this.image = image;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Vehicle setColor(String color) {
        this.color = color;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public Vehicle setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Vehicle setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Vehicle setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", markId='" + markId + '\'' +
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
