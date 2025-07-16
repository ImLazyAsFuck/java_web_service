package com.ss7.service.seed;

import com.ss7.model.entity.Seed;
import com.ss7.repo.SeedRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeedServiceImpl implements SeedService{

    private final SeedRepo seedRepo;

    @Override
    public List<Seed> findAll(){
        return seedRepo.findAll();
    }

    @Override
    public Seed findById(Long id){
        return seedRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seed not found with id = " + id));
    }

    @Override
    public Seed save(Seed seed){
        return seedRepo.save(seed);
    }

    @Override
    public void deleteById(Long id){
        if(findById(id) == null){
            throw new EntityNotFoundException("Seed not found with id = " + id);
        }
        seedRepo.deleteById(id);
    }

    @Override
    public Seed update(Seed seed){
        Seed exist = findById(seed.getId());
        exist.setName(seed.getName());
        exist.setQuantity(seed.getQuantity());
        return seedRepo.save(exist);
    }
}
