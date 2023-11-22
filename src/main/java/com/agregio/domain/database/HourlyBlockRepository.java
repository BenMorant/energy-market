package com.agregio.domain.database;

import com.agregio.domain.database.entity.HourlyBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourlyBlockRepository extends JpaRepository<HourlyBlockEntity, Long> {

}
