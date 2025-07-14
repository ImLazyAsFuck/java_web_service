package com.ss5.service.fruit;

import com.ss5.model.entity.Fruit;
import com.ss5.repo.FruitRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FruitServiceImpl implements FruitService{

    private final FruitRepo fruitRepo;

    @Override
    public Fruit createFruit(Fruit fruit){
        return fruitRepo.save(fruit);
    }

    @Override
    public List<Fruit> getAllFruits(){
        return fruitRepo.findAll();
    }

    @Override
    public Fruit getFruitById(Long id){
        return fruitRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Fruit not found with id: " + id));
    }

    @Override
    public Fruit updateFruit(Long id, Fruit fruit){
        Fruit existing = fruitRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Fruit not found with id: " + id));
        existing.setName(fruit.getName());
        existing.setPrice(fruit.getPrice());
        existing.setStock(fruit.getStock());
        existing.setStatus(fruit.getStatus());
        return fruitRepo.save(existing);
    }

    @Override
    public void deleteFruit(Long id){
        if (!fruitRepo.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete. Fruit not found with id: " + id);
        }
        fruitRepo.deleteById(id);
    }
}
