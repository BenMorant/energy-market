package com.agregio.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HourlyBlockResponse(Long id, Long farmId, BigDecimal energyAmount, BigDecimal floorPrice, LocalDate start,
                                  int hourCount) {
}
