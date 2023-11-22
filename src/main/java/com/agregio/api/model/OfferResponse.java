package com.agregio.api.model;

import java.util.List;

public record OfferResponse(Long id, String marketTypeAsString, String name,
                            List<HourlyBlockResponse> hourlyBlockResponses) {
}
