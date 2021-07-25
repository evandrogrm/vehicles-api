package com.example.vehicles.api.v1.repository;

import com.example.vehicles.domain.Mark;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepositoryV1 extends PagingAndSortingRepository<Mark, String>, JpaSpecificationExecutor<Mark> {
    @Query("SELECT m FROM Mark m WHERE m.name = ?1")
    Mark findByName(String name);
}
