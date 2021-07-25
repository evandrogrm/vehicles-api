package com.example.vehicles.api.v1.repository;

import com.example.vehicles.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepositoryV1 extends PagingAndSortingRepository<Vehicle, String>, JpaSpecificationExecutor<Vehicle> {
}
