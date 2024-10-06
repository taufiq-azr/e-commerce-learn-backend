package com.azharstudios.e_commerce_learn_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    private String review;

    @OneToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;
}
