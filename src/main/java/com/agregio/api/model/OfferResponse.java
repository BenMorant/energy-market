package com.agregio.api.model;

import com.agregio.domain.model.HourlyBlock;
import com.agregio.domain.model.MarketType;

import java.util.List;

public record OfferResponse(Long id, MarketType marketType, String name, List<HourlyBlockResponse> hourlyBlockResponses) {
}
