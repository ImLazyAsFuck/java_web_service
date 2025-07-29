package com.ss16.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Integer quantityTicket;

    @ManyToMany
    @JoinTable(
        name = "ticket_order_combos",
        joinColumns = @JoinColumn(name = "ticket_order_id"),
        inverseJoinColumns = @JoinColumn(name = "combo_id")
    )
    private List<Combo> combos;

    private BigDecimal totalMoney;

    private LocalDateTime createdAt;
}
