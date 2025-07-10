package com.ss3.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    public String name;

    @Column(unique = true, nullable = false, length = 50)
    public String email;

    @Column(unique = true, nullable = false, length = 20)
    public String phone;
    public double salary;

    @Column(name = "created_at", nullable = false)
    public LocalDate createdAt = LocalDate.now();
}
