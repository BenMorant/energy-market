package com.agregio.domain.database;

import com.agregio.domain.database.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

}
