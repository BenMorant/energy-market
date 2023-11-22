package com.agregio.domain;

import com.agregio.domain.model.MarketType;
import com.agregio.domain.model.Offer;

import java.util.List;

public interface OfferService {
    Offer create(Offer offer);

    List<Offer> getByMarket(MarketType marketType);
}
