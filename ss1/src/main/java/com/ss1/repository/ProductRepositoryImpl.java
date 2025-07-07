package com.ss1.repository;

import com.ss1.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class ProductRepositoryImpl implements ProductRepository{
    private final EntityManager em;
    @Override
    public List<Product> findAll(){
        return em.createQuery("select p from Product p", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id){
        return em.find(Product.class, id);
    }

    @Override
    public void save(Product product){
        em.persist(product);
    }

    @Override
    public void deleteById(Long id){
        if(findById(id) == null){
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
        em.remove(em.getReference(Product.class, id));
    }

    @Override
    public void update(Product product){
        em.merge(product);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return em.createQuery("select p from Product p where p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst();
    }


    public Optional<Product> findByNameNotIncludeId(String name, Long id){
        List<Product> result = em.createQuery("select p from Product p where p.name = :name and p.id <> :id", Product.class)
                .setParameter("name", name)
                .setParameter("id", id)
                .getResultList();
        return result.stream().findFirst();
    }
}
