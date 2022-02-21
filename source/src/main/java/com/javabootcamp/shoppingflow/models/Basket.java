package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "basket")
@Getter
@Setter
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "basket", fetch=FetchType.LAZY)
    private List<BasketProduct> basketProducts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
