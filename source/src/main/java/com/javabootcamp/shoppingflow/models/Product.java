package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private @Getter @Setter Long id;
    private @Getter @Setter String name;

    private @Getter @Setter String imageUrl;
    private @Getter @Setter double price;
    private @Getter @Setter double netPrice;

    private @Getter @Setter float rating;
    private @Getter @Setter int totalReviewer;

    private @Getter @Setter
    @OneToOne
    @JoinColumn(name = "merchant_id")
    Merchant merchant;
}
