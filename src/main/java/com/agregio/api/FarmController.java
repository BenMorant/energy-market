package com.agregio.api;

import com.agregio.api.model.FarmCreationRequest;
import com.agregio.api.model.FarmResponse;
import com.agregio.domain.FarmService;
import com.agregio.domain.model.Farm;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public FarmResponse createFarm(@RequestBody @Valid FarmCreationRequest farmCreationRequest) {
        return toFarmResponse(farmService.create(toFarm(farmCreationRequest)));
    }

    private FarmResponse toFarmResponse(Farm farm) {
        return new FarmResponse(farm.id(), farm.farmType().name(), farm.name());
    }

    private Farm toFarm(FarmCreationRequest farmCreationRequest) {
        return Farm.toCreate(farmCreationRequest.farmType(), farmCreationRequest.name());
    }
}