package com.agregio.database;

import com.agregio.database.entity.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<FarmEntity, Long> {
}
