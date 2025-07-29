package com.ss16.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    private Boolean isLogin;
    private Boolean isStatus;
}
