package com.example.vehicles.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rents")
@EntityListeners(AuditingEntityListener.class)
public class Rent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Column(name = "vehicle_id", length = 36, nullable = false)
    private String vehicleId;

    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_rent_vehicle_id"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Vehicle vehicle;

    @Column(name = "user_id", length = 36, nullable = false)
    private String userId;

    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_rent_user_id"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private User user;

    @Column(name = "rent_start_at", nullable = false)
    private Date rentStartAt;

    @Column(name = "rent_end_at", nullable = false)
    private Date rentEndAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public Rent setId(String id) {
        this.id = id;
        return this;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public Rent setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Rent setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Rent setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Rent setUser(User user) {
        this.user = user;
        return this;
    }

    public Date getRentStartAt() {
        return rentStartAt;
    }

    public Rent setRentStartAt(Date rentStartAt) {
        this.rentStartAt = rentStartAt;
        return this;
    }

    public Date getRentEndAt() {
        return rentEndAt;
    }

    public Rent setRentEndAt(Date rentEndAt) {
        this.rentEndAt = rentEndAt;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Rent setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Rent setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id='" + id + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", userId='" + userId + '\'' +
                ", rentStartAt=" + rentStartAt +
                ", rentEndAt=" + rentEndAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
