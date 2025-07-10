package com.ss3.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "city",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "country_id"})
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Season season;

    @Min(value = 1, message = "Area must be greater than 0")
    @Column(nullable = false)
    private float area;

    @Min(value = 1, message = "Population must be greater than 0")
    @Column(nullable = false)
    private int population;
}
