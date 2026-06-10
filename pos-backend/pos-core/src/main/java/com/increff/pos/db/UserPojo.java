package com.increff.pos.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pos_users", indexes = {
        @Index(name = "idx_user_email", columnList = "email", unique = true)
})
@Getter
@Setter
public class UserPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 50)
    private String role; // Values: "operator" or "supervisor" (Stored in lowercase)
}