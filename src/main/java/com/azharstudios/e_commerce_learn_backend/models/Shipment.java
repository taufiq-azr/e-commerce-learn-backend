package com.azharstudios.e_commerce_learn_backend.models;

import com.azharstudios.e_commerce_learn_backend.models.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private Long shipmentId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private String paymentMethod;

    private String customerAddress;

    private Double productPrice;

    private Double deliveryCost;

    private Double discount;

    private Double finalPrice;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;  // New field for tracking shipment status
}
