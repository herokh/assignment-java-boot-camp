package com.javabootcamp.shoppingflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private @Getter @Setter int id;
    private @Getter @Setter String username;
    private @Getter @Setter String password;
}
