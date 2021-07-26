package com.example.vehicles.api.v1.repository;

import com.example.vehicles.domain.Rent;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RentRepositoryV1 extends PagingAndSortingRepository<Rent, String>, JpaSpecificationExecutor<Rent> {

    @Query("SELECT r FROM Rent r " +
            "WHERE r.vehicleId = ?1 " +
            "AND ( (?2 BETWEEN r.rentStartAt AND r.rentEndAt) OR (?3 BETWEEN r.rentStartAt AND r.rentEndAt) )")
    Rent findByVehicleBetweenDates(String vehicleId, Date rentStartAt, Date rentEndAt);

    @Query("SELECT r FROM Rent r " +
            "WHERE r.userId = ?1 " +
            "AND ( (?2 BETWEEN r.rentStartAt AND r.rentEndAt) OR (?3 BETWEEN r.rentStartAt AND r.rentEndAt) )")
    Rent findByUserBetweenDates(String userId, Date rentStartAt, Date rentEndAt);
}
