package com.agregio.domain.model;

import com.agregio.exception.FarmTypeException;

public record Farm
        (Long id, FarmType farmType, String name) {

    public static Farm toCreate(String farmTypeAsString, String name) {
        return new Farm(null, toFarmType(farmTypeAsString), name);
    }

    private static FarmType toFarmType(String farmTypeAsString) {
        return switch (farmTypeAsString.toLowerCase()) {
            case "wind" -> FarmType.WIND;
            case "solar" -> FarmType.SOLAR;
            case "hydro" -> FarmType.HYDRO;
            default -> throw new FarmTypeException(farmTypeAsString);
        };
    }

}