package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "merchant")
@Getter
@Setter
public class Merchant {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "merchant", fetch=FetchType.LAZY)
    private List<Product> products;
}
