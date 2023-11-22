package com.agregio.api.model;

import java.util.List;

public record OfferCreationRequest(String market, String name,
                                   List<HourlyBlockCreationRequest> hourlyBlockCreationRequests) {
}
