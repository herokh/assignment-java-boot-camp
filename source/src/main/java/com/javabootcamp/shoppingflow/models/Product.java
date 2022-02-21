package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
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

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Merchant.class)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id", nullable = false)
    private Merchant merchant;

    @OneToOne
    @JoinColumn(name = "product_brand_id")
    private ProductBrand productBrand;

    @OneToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;
}
