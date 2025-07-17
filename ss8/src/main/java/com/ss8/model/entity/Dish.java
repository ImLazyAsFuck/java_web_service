package com.ss8.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Dish{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "DECIMAL(15,2)")
    private double price;

    @Column(nullable = false, length = 20)
    private String status = "ACTIVE";

    @Column(nullable = false, length = 500)
    private String image;

    public Dish(Object o, @NotBlank(message = "Name is required") String name, String description, Double price, String imageUrl){
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = imageUrl;
    }
}
