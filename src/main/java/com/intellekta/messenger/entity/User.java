package com.intellekta.messenger.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username; // Логин, неизменяемый

    @Column(nullable = false)
    private String password; // Пароль

    @Column(nullable = true)
    private String nickname; // Никнейм, который можно изменять на странице home
}
