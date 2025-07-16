package com.ss7.service.harvest;

import com.ss7.model.entity.Harvest;

import java.util.List;

public interface HarvestService{
    List<Harvest> getAllHarvests();
    Harvest getHarvestById(Long id);
    Harvest addHarvest(Harvest harvest);
}
