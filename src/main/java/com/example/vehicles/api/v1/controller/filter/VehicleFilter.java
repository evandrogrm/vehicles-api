package com.example.vehicles.api.v1.controller.filter;

import com.example.vehicles.domain.Vehicle;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

@Join(path = "mark", alias = "m", type = JoinType.LEFT)
@And({
        @Spec(path = "id", spec = Equal.class),
        @Spec(path = "markId", spec = Equal.class),
        @Spec(path = "m.name", params = "markName", spec = LikeIgnoreCase.class),
        @Spec(path = "model", spec = LikeIgnoreCase.class),
        @Spec(
                path = "fabricationYear",
                params = {"fabricationYearFrom", "fabricationYearTo"},
                spec = Between.class
        ),
        @Spec(path = "color", spec = LikeIgnoreCase.class),
        @Spec(
                path = "mileage",
                params = {"mileageFrom", "mileageTo"},
                spec = Between.class
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
public interface VehicleFilter extends Specification<Vehicle> {
}

