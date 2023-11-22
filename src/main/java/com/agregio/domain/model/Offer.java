package com.agregio.domain.model;

import com.agregio.exception.HourlyBlockException;
import com.agregio.exception.MarketTypeException;

import java.util.List;

public record Offer(Long id, MarketType marketType, String name, List<HourlyBlock> hourlyBlocks) {

    public static Offer toCreate(String marketTypeAsString, String name, List<HourlyBlock> hourlyBlocksToCreate) {
        if (hourlyBlocksToCreate == null || hourlyBlocksToCreate.isEmpty()) {
            throw new HourlyBlockException("Hourly blocks shouldn't be null nor empty");
        } else {
            return new Offer(null, toMarketType(marketTypeAsString), name, hourlyBlocksToCreate);
        }
    }

    private static MarketType toMarketType(String marketTypeAsString) {
        return switch (marketTypeAsString.toLowerCase()) {
            case "primary" -> MarketType.PRIMARY;
            case "secondary" -> MarketType.SECONDARY;
            case "rapid" -> MarketType.RAPID;
            default -> throw new MarketTypeException("Unexpected value: " + marketTypeAsString);

        };
    }
}
