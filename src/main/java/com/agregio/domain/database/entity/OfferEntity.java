package com.agregio.domain.database.entity;

import com.agregio.domain.model.MarketType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "OFFERS")
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MarketType marketType;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private List<HourlyBlockEntity> hourlyBlockEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MarketType getMarketType() {
        return marketType;
    }

    public void setMarketType(MarketType marketType) {
        this.marketType = marketType;
    }

    public List<HourlyBlockEntity> getHourlyBlockEntities() {
        return hourlyBlockEntities;
    }

    public void setHourlyBlockEntities(List<HourlyBlockEntity> hourlyBlockEntities) {
        this.hourlyBlockEntities = hourlyBlockEntities;
    }


}
