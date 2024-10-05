package com.azharstudios.e_commerce_learn_backend.controller;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.Order;
import com.azharstudios.e_commerce_learn_backend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Mendapatkan semua order
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        try{
        List<Order> orders = orderService.findAllOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Mendapatkan order berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long orderId) {
        try {
            Order order = orderService.findOrderById(orderId);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (NotFoundException e){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
    }

    // Mendapatkan order berdasarkan ID produk
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Order>> getOrdersByProductId(@PathVariable Long productId) {
        try {
        List<Order> orders = orderService.findOrderByProductId(productId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Mendapatkan order berdasarkan ID review
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<Order>> getOrdersByReviewId(@PathVariable Long reviewId) {
        try {
        List<Order> orders = orderService.findOrderByReviewId(reviewId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Membuat order baru
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Mengupdate order
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long orderId, @RequestBody Order orderUpdated) {
        try {
        Order updatedOrder = orderService.updateOrder(orderId, orderUpdated);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Menghapus order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long orderId) {
        try {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
