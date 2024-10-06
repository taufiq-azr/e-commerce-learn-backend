package com.azharstudios.e_commerce_learn_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopId")
    private Long shopId;

    @Column(nullable = false)
    private String shopName;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Product> products;
}
