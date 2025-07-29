package com.ss16.model.entity;

import com.ss16.model.enums.PlayAreaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayArea {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private Integer maxCapacity;

    @Enumerated(EnumType.STRING)
    private PlayAreaStatus status;
}
