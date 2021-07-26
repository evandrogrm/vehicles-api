package com.example.vehicles.api.v1.controller.filter;

import com.example.vehicles.domain.Rent;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

@Join(path = "vehicle", alias = "v", type = JoinType.LEFT)
@Join(path = "user", alias = "u", type = JoinType.LEFT)
@And({
        @Spec(path = "id", spec = Equal.class),
        // Vehicle
        @Spec(path = "vehicleId", spec = Equal.class),
        @Spec(path = "v.markId", params = "vehicleMarkId", spec = Equal.class),
        @Spec(path = "v.model", params = "vehicleModel", spec = LikeIgnoreCase.class),
        @Spec(path = "v.fabricationYear", params = "vehicleFabricationYear", spec = Equal.class),
        @Spec(path = "v.color", params = "vehicleColor", spec = LikeIgnoreCase.class),
        @Spec(path = "v.mileage", params = "vehicleMileage", spec = Equal.class),
        // User
        @Spec(path = "userId", spec = Equal.class),
        @Spec(path = "u.name", params = "userName", spec = LikeIgnoreCase.class),
        @Spec(path = "u.email", params = "userEmail", spec = LikeIgnoreCase.class),
        // rentStartAt
        @Spec(
                path = "rentStartAt",
                params = {"rentStartAtFrom", "rentStartAtTo"},
                spec = Between.class,
                config = {"yyyy-MM-dd hh:mm:ss"}
        ),
        // rentEndAt
        @Spec(
                path = "rentEndAt",
                params = {"rentEndAtFrom", "rentEndAtTo"},
                spec = Between.class,
                config = {"yyyy-MM-dd hh:mm:ss"}
        ),

        @Spec(
                path = "createdAt",
                params = {"createdAtFrom", "createdAtTo"},
                spec = Between.class,
                config = {"yyyy-MM-dd hh:mm:ss"}
        ),
        @Spec(
                path = "updatedAt",
                params = {"updatedAtFrom", "updatedAtTo"},
                spec = Between.class,
                config = {"yyyy-MM-dd hh:mm:ss"}
        ),
})
public interface RentFilter extends Specification<Rent> {
}

