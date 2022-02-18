package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private @Getter @Setter Long id;

    private @Getter @Setter Long productId;
    private @Getter @Setter int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private @Getter @Setter User user;
}
