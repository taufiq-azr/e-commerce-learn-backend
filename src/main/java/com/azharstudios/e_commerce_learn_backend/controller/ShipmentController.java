package com.azharstudios.e_commerce_learn_backend.controller;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.Shipment;
import com.azharstudios.e_commerce_learn_backend.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService){
        this.shipmentService = shipmentService;
    }

    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        try {
            List<Shipment> shipments = shipmentService.findAllShipment();
            return new ResponseEntity<>(shipments, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable Long id){
        try {
            Shipment shipment = shipmentService.findShipmentById(id);
            return new ResponseEntity<>(shipment, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<Shipment>> findShipmentByOrderId(@PathVariable Long orderId){
        try {
            List<Shipment> shipmentByOrderId = shipmentService.findShipmentByOrderId(orderId);
            return new ResponseEntity<>(shipmentByOrderId, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<Shipment> createShipment(@PathVariable Long orderId, @RequestBody Shipment shipmentData){
        try {
            Shipment shipmentCreated = shipmentService.createShipment(orderId, shipmentData);
            return new ResponseEntity<>(shipmentCreated, HttpStatus.CREATED);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable Long id, @RequestBody Shipment shipment) {
        try {
            Shipment updatedShipment = shipmentService.updateShipment(id, shipment);
            return new ResponseEntity<>(updatedShipment, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        try {
        shipmentService.deleteShipment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
