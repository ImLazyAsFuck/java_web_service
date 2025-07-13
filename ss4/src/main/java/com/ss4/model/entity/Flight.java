package com.ss4.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String flightNumber;

    @Column(nullable = false, length = 50)
    private String departure;

    @Column(nullable = false, length = 50)
    private String destination;

    @Column(nullable = false)
    private double price;
}

