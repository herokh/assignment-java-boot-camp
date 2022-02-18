package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class ProductCategory {
    @Id
    @Column(name = "id", nullable = false)
    private @Getter @Setter Long id;
    private @Getter @Setter String name;
}