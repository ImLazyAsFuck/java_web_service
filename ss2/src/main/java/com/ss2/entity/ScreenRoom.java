package com.ss2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @OneToMany(mappedBy = "screenRoom", cascade = CascadeType.ALL)
    private List<Seat> seats;

    @OneToMany(mappedBy = "screenRoom", cascade = CascadeType.ALL)
    private List<Schedule> schedules;
}
