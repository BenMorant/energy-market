package com.agregio.domain;

import com.agregio.domain.database.OfferRepository;
import com.agregio.domain.database.entity.HourlyBlockEntity;
import com.agregio.domain.database.entity.OfferEntity;
import com.agregio.domain.model.HourlyBlock;
import com.agregio.domain.model.MarketType;
import com.agregio.domain.model.Offer;
import com.agregio.exception.HourlyBlockException;
import com.agregio.exception.MarketTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Offer service")
class OfferServiceImplTest {

    @Mock
    private OfferRepository repository;

    @InjectMocks
    private OfferServiceImpl offerService;


    @Test
    @DisplayName("should create an offer")
    void shouldCreateOffer() {
        LocalDate date = LocalDate.now();
        HourlyBlockEntity hourlyBlockEntity = new HourlyBlockEntity();
        hourlyBlockEntity.setId(1L);
        hourlyBlockEntity.setFloorPrice(BigDecimal.TWO);
        hourlyBlockEntity.setHourCount(4);
        hourlyBlockEntity.setStart(date);
        hourlyBlockEntity.setFarmId(1L);
        hourlyBlockEntity.setEnergyAmount(BigDecimal.TEN);
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setMarketType(MarketType.PRIMARY);
        offerEntity.setName("name");
        offerEntity.setHourlyBlockEntities(List.of(hourlyBlockEntity));

        HourlyBlock hourlyBlockToBeCreated = HourlyBlock.toCreate(1L, BigDecimal.TEN, BigDecimal.TWO, date, 4);
        Offer toBeCreated = Offer.toCreate("primary", "name", List.of(hourlyBlockToBeCreated));
        when(repository.save(any(OfferEntity.class))).thenReturn(offerEntity);
        Offer result = offerService.create(toBeCreated);
        assertThat(result.name()).isEqualTo(offerEntity.getName());
        assertThat(result.marketType()).isEqualTo(offerEntity.getMarketType());
        verify(repository).save(any());
        verifyNoMoreInteractions(repository);

    }

    @Test
    @DisplayName("should throw a HourlyBlockException when HourlyBlock is empty")
    void shouldThrowHourlyBlockException_whenEmpty() {
        assertThatThrownBy(() -> {
            offerService.create(Offer.toCreate("primary", "name", new ArrayList<>()));
        }).isInstanceOf(HourlyBlockException.class)
                .hasMessageContaining("Hourly blocks shouldn't be null");
    }

    @Test
    @DisplayName("should throw a HourlyBlockException when HourlyBlock is null")
    void shouldThrowHourlyBlockException_whenNull() {
        assertThatThrownBy(() -> {
            offerService.create(Offer.toCreate("primary", "name", null));
        }).isInstanceOf(HourlyBlockException.class)
                .hasMessageContaining("Hourly blocks shouldn't be null");
    }


    @Test
    @DisplayName("should throw a MarketTypeException when MarketType doesn't exist")
    void shouldThrowMarketTypeException() {
        HourlyBlock hourlyBlockToCreate = HourlyBlock.toCreate(1L, BigDecimal.TEN, BigDecimal.TWO, LocalDate.now(), 4);

        assertThatThrownBy(() -> {
            offerService.create(Offer.toCreate("toto", "name", List.of(hourlyBlockToCreate)));
        }).isInstanceOf(MarketTypeException.class)
                .hasMessageContaining("Unexpected value:");
    }


}