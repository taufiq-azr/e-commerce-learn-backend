package com.azharstudios.e_commerce_learn_backend.services;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.Order;
import com.azharstudios.e_commerce_learn_backend.models.Product;
import com.azharstudios.e_commerce_learn_backend.models.Review;
import com.azharstudios.e_commerce_learn_backend.models.enums.OrderStatus;
import com.azharstudios.e_commerce_learn_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAllOrder(){
        return orderRepository.findAll();
    }

    public Order findOrderById(Long order_id) {
        return orderRepository.findById(order_id).orElseThrow(
                () -> new NotFoundException("Order Not Found !"));
    }

    public List<Order> findOrderByProductId(Long productId){
        return orderRepository.findOrderByProductId(productId);
    }

    public List<Order> findOrderByReviewId(Long reviewId){
        return orderRepository.findOrderByReviewId(reviewId);
    }

    public Order createOrder(Order order) {
        // Hitung total harga berdasarkan orderItems
        double totalPrice = order.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, Order orderUpdated) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order Not Found"));

        existingOrder.setPaymentMethod(orderUpdated.getPaymentMethod());
        existingOrder.setStatus(orderUpdated.getStatus());

        // Hitung ulang total harga berdasarkan orderItems yang baru
        double totalPrice = orderUpdated.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        existingOrder.setTotalPrice(totalPrice);

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long order_id) {
        Order order = orderRepository.findById(order_id).orElseThrow(
                () -> new NotFoundException("Order Not Found, with Id " + order_id));

        // Allow canceling only if the order is still PENDING
        if (order.getStatus() == OrderStatus.PENDING) {
            orderRepository.deleteById(order_id);
        } else {
            throw new IllegalStateException("Cannot delete an order that has already been processed.");
        }
    }


}
