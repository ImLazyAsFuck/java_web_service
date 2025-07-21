package com.ss10.model.entity;

import com.ss10.model.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Account{
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 50)
    private String fullName;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, unique = true, length = 15)
    private String phone;

    @Column(nullable = false, unique = true, length = 12)
    private String cccd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Column(nullable = false, columnDefinition = "DECIMAL(15,2)")
    private Double money;

}
