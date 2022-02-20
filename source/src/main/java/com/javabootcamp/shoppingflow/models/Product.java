package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter 
@Setter
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    private String imageUrl;
    private double price;
    private double netPrice;

    private float rating;
    private int totalReviewer;

    @OneToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @OneToOne
    @JoinColumn(name = "product_brand_id")
    private ProductBrand productBrand;

    @OneToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;
}
