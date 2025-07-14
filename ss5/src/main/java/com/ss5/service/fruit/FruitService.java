package com.ss5.service.fruit;

import com.ss5.model.entity.Fruit;

import java.util.List;

public interface FruitService{
    List<Fruit> getAllFruits();
    Fruit getFruitById(Long id);
    Fruit createFruit(Fruit fruit);
    Fruit updateFruit(Long id, Fruit fruit);
    void deleteFruit(Long id);
}
