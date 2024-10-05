package com.azharstudios.e_commerce_learn_backend.controller;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.azharstudios.e_commerce_learn_backend.models.OrderItem;
import com.azharstudios.e_commerce_learn_backend.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-items")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    // Mendapatkan semua order items
    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        try {
            List<OrderItem> orderItems = orderItemService.findAllOrderItems();
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } catch (NotFoundException e){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
    }

    // Mendapatkan order item berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable("id") Long orderItemId) {
        try {
        OrderItem orderItem = orderItemService.findOrderItemById(orderItemId);
        return new ResponseEntity<>(orderItem, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Membuat order item baru
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        try {
        OrderItem createdOrderItem = orderItemService.createOrderItem(orderItem);
        return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Mengupdate order item
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable("id") Long orderItemId, @RequestBody OrderItem orderItemUpdated) {
        try {
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(orderItemId, orderItemUpdated);
        return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Menghapus order item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable("id") Long orderItemId) {
        try {
        orderItemService.deleteOrderItem(orderItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
