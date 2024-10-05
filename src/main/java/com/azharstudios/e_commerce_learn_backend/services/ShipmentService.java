package com.azharstudios.e_commerce_learn_backend.services;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.Order;
import com.azharstudios.e_commerce_learn_backend.models.Shipment;
import com.azharstudios.e_commerce_learn_backend.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final OrderService orderService;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository, OrderService orderService) {
        this.shipmentRepository = shipmentRepository;
        this.orderService = orderService;
    }


    public List<Shipment> findAllShipment(){
        return shipmentRepository.findAll();
    }

    public Shipment findShipmentById(Long shipment){
        return shipmentRepository.findById(shipment).orElseThrow(
                ()-> new NotFoundException("Shipment Not Found"));
    }

    public List<Shipment> findShipmentByOrderId(Long orderId) {
        return shipmentRepository.findShipmentByOrderId(orderId);
    }

    public Shipment createShipment(Long orderId, Shipment shipment) {
        // Mendapatkan Order berdasarkan ID
        Order order = orderService.findOrderById(orderId);
        shipment.setOrder(order); // Menghubungkan shipment dengan order

        // Menghitung final price jika diperlukan
        Double finalPrice = shipment.getDeliveryCost() - shipment.getDiscount();
        shipment.setFinalPrice(finalPrice);

        return shipmentRepository.save(shipment);
    }

    public Shipment updateShipment(Long shipmentId, Shipment shipmentUpdated) {
        Shipment existingShipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new NotFoundException("Shipment Not Found, with Id " + shipmentId));

        existingShipment.setCustomerAddress(shipmentUpdated.getCustomerAddress());
        existingShipment.setDeliveryCost(shipmentUpdated.getDeliveryCost());
        existingShipment.setDiscount(shipmentUpdated.getDiscount());

        // Menghitung ulang final price
        Double finalPrice = shipmentUpdated.getDeliveryCost() - shipmentUpdated.getDiscount();
        existingShipment.setFinalPrice(finalPrice);

        if (shipmentUpdated.getStatus() != null) {
            existingShipment.setStatus(shipmentUpdated.getStatus());
        }

        return shipmentRepository.save(existingShipment);
    }

    public void deleteShipment(Long shipmentId) {
        if (!shipmentRepository.existsById(shipmentId)) {
            throw new NotFoundException("Shipment Not Found, with Id " + shipmentId);
        }
        shipmentRepository.deleteById(shipmentId);
    }
}
