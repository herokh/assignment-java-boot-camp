package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_address")
@Getter
@Setter
public class OrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String addressNumber;
    private String subdistrict;
    private String district;
    private String province;
    private String zipCode;

    private String email;
    private String tel;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
