package com.agregio.database;

import com.agregio.database.entity.HourlyBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourlyBlockRepository extends JpaRepository<HourlyBlockEntity, Long> {

}
