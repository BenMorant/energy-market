package com.agregio.domain;

import com.agregio.database.FarmRepository;
import com.agregio.database.entity.FarmEntity;
import com.agregio.domain.model.Farm;
import org.springframework.stereotype.Service;

@Service
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;

    public FarmServiceImpl(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public Farm create(Farm farm) {
        return toFarm(farmRepository.save(toFarmEntity(farm)));
    }

    private Farm toFarm(FarmEntity farmEntity) {
        return new Farm(farmEntity.getId(), farmEntity.getFarmType(), farmEntity.getName());
    }

    private FarmEntity toFarmEntity(Farm farm) {
        FarmEntity farmEntity = new FarmEntity();
        farmEntity.setFarmType(farm.farmType());
        farmEntity.setName(farm.name());
        return farmEntity;
    }
}
