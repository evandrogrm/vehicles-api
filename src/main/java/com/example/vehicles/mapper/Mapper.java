package com.example.vehicles.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public abstract class Mapper {

    private final ModelMapper mapper;

    public Mapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public <T> T map(Object obj, Class<T> clazz) {
        return mapper.map(obj, clazz);
    }
}
