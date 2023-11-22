package com.agregio.api;

import com.agregio.api.model.HourlyBlockCreationRequest;
import com.agregio.api.model.HourlyBlockResponse;
import com.agregio.api.model.OfferCreationRequest;
import com.agregio.api.model.OfferResponse;
import com.agregio.domain.OfferService;
import com.agregio.domain.model.HourlyBlock;
import com.agregio.domain.model.MarketType;
import com.agregio.domain.model.Offer;
import com.agregio.exception.MarketTypeException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OfferResponse createOffer(@RequestBody @Valid OfferCreationRequest offerCreationRequest) {
        return toOfferResponse(offerService.create(toOffer(offerCreationRequest)));
    }

    private OfferResponse toOfferResponse(Offer offer) {
        return new OfferResponse(offer.id(), offer.marketType().name(), offer.name(), offer.hourlyBlocks().stream().map(this::toHourlyBlockResponse).toList());
    }

    private HourlyBlockResponse toHourlyBlockResponse(HourlyBlock hourlyBlock) {
        return new HourlyBlockResponse(hourlyBlock.id(), hourlyBlock.farmId(), hourlyBlock.energyAmount(), hourlyBlock.floorPrice(), hourlyBlock.start(), hourlyBlock.hourCount());
    }

    private Offer toOffer(OfferCreationRequest offerCreationRequest) {
        return Offer.toCreate(offerCreationRequest.market(), offerCreationRequest.name(), offerCreationRequest.hourlyBlockCreationRequests().stream().map(this::toHourlyBlock).toList());
    }

    private HourlyBlock toHourlyBlock(HourlyBlockCreationRequest hourlyBlockCreationRequest) {
        return HourlyBlock.toCreate(hourlyBlockCreationRequest.farmId(), hourlyBlockCreationRequest.energyAmount(), hourlyBlockCreationRequest.floorPrice(), hourlyBlockCreationRequest.start(), hourlyBlockCreationRequest.hourCount());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<OfferResponse> getOffersByMarket(@RequestParam @Valid String market) {
        return offerService.getByMarket(toMarketType(market)).stream().map(this::toOfferResponse).toList();
    }

    private MarketType toMarketType(String marketTypeAsString) {
        return switch (marketTypeAsString.toLowerCase()) {
            case "primary" -> MarketType.PRIMARY;
            case "secondary" -> MarketType.SECONDARY;
            case "rapid" -> MarketType.RAPID;
            default -> throw new MarketTypeException("Unexpected value: " + marketTypeAsString);

        };
    }
}
