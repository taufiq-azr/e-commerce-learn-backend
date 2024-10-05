package com.azharstudios.e_commerce_learn_backend.models;

import com.azharstudios.e_commerce_learn_backend.models.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;  // Ubah nama field sesuai konvensi

    private Double totalPrice;

    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  // Status pesanan

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;  // Mengganti produk dengan order items

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Shipment shipment;  // Hubungan satu ke satu dengan Shipment

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Review review;

}
