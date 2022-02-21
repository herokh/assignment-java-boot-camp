package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@AllArgsConstructor
public class Payment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String paymentMethodName;
    private String paymentMethodSlug;

    private boolean isEnabled;

    public Payment() {
    }
}
