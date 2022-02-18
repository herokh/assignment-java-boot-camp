package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private @Getter @Setter Long id;

    @Column(unique = true)
    private @Getter @Setter String username;
    private @Getter @Setter String password;
}
