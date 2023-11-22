package com.agregio.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HourlyBlock(Long id, Long farmId, BigDecimal energyAmount, BigDecimal floorPrice, LocalDate start, int hourCount) {
    public static HourlyBlock toCreate(Long farmId, BigDecimal energyAmount, BigDecimal floorPrice, LocalDate start, int hourCount) {
        return new HourlyBlock(null, farmId, energyAmount, floorPrice, start, hourCount);
    }
}
