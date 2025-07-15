package com.ss6.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, unique = true, nullable = false)
    private String name;
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    @Column(length = 100)
    private String position;
    @Column(columnDefinition = "DECIMAL(15,2)")
    private Double salary;
}
