package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private double amount;
    private int quantity;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;
}
