package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String invoiceNumber;
    private double totalAmount;
    private Date orderDateTime;
    private Date paymentExpiredDateTime;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User payer;

    @OneToOne
    @JoinColumn(name = "order_payment_id")
    private OrderPayment orderPayment;

    @OneToOne
    @JoinColumn(name = "order_address_id")
    private OrderAddress orderAddress;

    @OneToMany(mappedBy = "order", fetch=FetchType.LAZY)
    private List<OrderProduct> orderProducts;
}
