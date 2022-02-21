package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_category")
@Getter
@Setter
public class ProductCategory {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
}
