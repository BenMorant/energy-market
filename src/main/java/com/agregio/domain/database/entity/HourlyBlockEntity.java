package com.agregio.domain.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "HOURLY_BLOCKS")
public class HourlyBlockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    private Long farmId;

    private BigDecimal energyAmount;

    private BigDecimal floorPrice;

    private LocalDate start;
    private int hourCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public BigDecimal getEnergyAmount() {
        return energyAmount;
    }

    public void setEnergyAmount(BigDecimal energyAmount) {
        this.energyAmount = energyAmount;
    }

    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(BigDecimal floorPrice) {
        this.floorPrice = floorPrice;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public int getHourCount() {
        return hourCount;
    }

    public void setHourCount(int hourCount) {
        this.hourCount = hourCount;
    }
}
