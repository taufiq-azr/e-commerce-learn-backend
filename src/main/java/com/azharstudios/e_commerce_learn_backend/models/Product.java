package com.azharstudios.e_commerce_learn_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_price")
    private Double price;

    private Integer unit;

    @OneToOne
    @JoinColumn(name = "product_category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

}
