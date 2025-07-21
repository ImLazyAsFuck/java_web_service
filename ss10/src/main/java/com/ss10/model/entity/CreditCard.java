package com.ss10.model.entity;

import com.ss10.model.enums.CreditCardStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreditCard {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    @Column(nullable = false)
    private Double spendingLimit;

    @Column(nullable = false)
    private Double amountSpent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CreditCardStatus status;
}
