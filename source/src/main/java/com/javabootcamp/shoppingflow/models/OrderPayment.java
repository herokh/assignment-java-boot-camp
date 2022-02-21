package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_payment")
@Getter
@Setter
public class OrderPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String paymentMethod;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
