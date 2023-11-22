package com.agregio.database;

import com.agregio.database.entity.OfferEntity;
import com.agregio.domain.model.MarketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

    List<OfferEntity> findByMarketType(MarketType marketType);
}
