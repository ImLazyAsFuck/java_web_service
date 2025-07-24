package com.ss13.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User{
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = true, length = 100)
    private String username;


}
