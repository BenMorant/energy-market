package com.agregio.domain;

import com.agregio.database.OfferRepository;
import com.agregio.database.entity.HourlyBlockEntity;
import com.agregio.database.entity.OfferEntity;
import com.agregio.domain.model.HourlyBlock;
import com.agregio.domain.model.MarketType;
import com.agregio.domain.model.Offer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;


    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    @Override
    public Offer create(Offer offer) {
        return toOffer(offerRepository.save(toOfferEntity(offer)));
    }

    @Override
    public List<Offer> getByMarket(MarketType marketType) {
        return offerRepository.findByMarketType(marketType).stream().map(this::toOffer).toList();
    }

    private Offer toOffer(OfferEntity offerEntity) {
        return new Offer(offerEntity.getId(), offerEntity.getMarketType(), offerEntity.getName(), offerEntity.getHourlyBlockEntities().stream().map(this::toHourlyBlock).toList());
    }

    private HourlyBlock toHourlyBlock(HourlyBlockEntity hourlyBlockEntity) {
        return new HourlyBlock(hourlyBlockEntity.getId(), hourlyBlockEntity.getFarmId(), hourlyBlockEntity.getEnergyAmount(), hourlyBlockEntity.getFloorPrice(), hourlyBlockEntity.getStart(), hourlyBlockEntity.getHourCount());
    }

    private OfferEntity toOfferEntity(Offer offer) {
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setMarketType(offer.marketType());
        offerEntity.setName(offer.name());
        offerEntity.setHourlyBlockEntities(offer.hourlyBlocks().stream().map(this::ToHourlyBlockEntity).toList());
        return offerEntity;
    }

    private HourlyBlockEntity ToHourlyBlockEntity(HourlyBlock hourlyBlock) {
        HourlyBlockEntity hourlyBlockEntity = new HourlyBlockEntity();
        hourlyBlockEntity.setFarmId(hourlyBlock.farmId());
        hourlyBlockEntity.setEnergyAmount(hourlyBlock.energyAmount());
        hourlyBlockEntity.setFloorPrice(hourlyBlock.floorPrice());
        hourlyBlockEntity.setStart(hourlyBlock.start());
        hourlyBlockEntity.setHourCount(hourlyBlock.hourCount());
        return hourlyBlockEntity;
    }
}
