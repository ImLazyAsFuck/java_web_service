package com.ss5.repo;

import com.ss5.model.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepo extends JpaRepository<Fruit, Long>{
    Fruit findById(long id);
}
