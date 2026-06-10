package com.increff.pos.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pos_clients", indexes = {
        @Index(name = "idx_client_name", columnList = "name", unique = true)
})
@Getter
@Setter
public class ClientPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name; // Stored purely in trimmed lowercase format
}