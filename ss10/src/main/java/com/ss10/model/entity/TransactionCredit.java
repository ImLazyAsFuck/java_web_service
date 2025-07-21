package com.ss10.model.entity;

import jakarta.persistence.*;
import lombok.*;
import com.ss10.model.enums.TransactionStatus;

import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionCredit {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Account accountReceiver;

    @ManyToOne
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCardSender;

    private String note;

    @Column(nullable = false)
    private Double money;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;
}