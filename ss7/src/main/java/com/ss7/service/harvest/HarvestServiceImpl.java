package com.ss7.service.harvest;

import com.ss7.model.entity.Harvest;
import com.ss7.repo.HarvestRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService{

    private final HarvestRepo harvestRepo;

    @Override
    public List<Harvest> getAllHarvests(){
        return harvestRepo.findAll();
    }

    @Override
    public Harvest getHarvestById(Long id){
        return harvestRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found with id = " + id));
    }

    @Override
    public Harvest addHarvest(Harvest harvest){
        return harvestRepo.save(harvest);
    }
}
