package com.ss2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private Integer duration;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Schedule> schedules;
}
