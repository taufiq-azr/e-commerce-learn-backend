package com.azharstudios.e_commerce_learn_backend.services;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.OrderItem;
import com.azharstudios.e_commerce_learn_backend.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem findOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new NotFoundException("Order Item Not Found"));
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(Long orderItemId, OrderItem orderItemUpdated) {
        OrderItem existingOrderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new NotFoundException("Order Item Not Found"));

        existingOrderItem.setQuantity(orderItemUpdated.getQuantity());
        existingOrderItem.setPrice(orderItemUpdated.getPrice());
        existingOrderItem.setProduct(orderItemUpdated.getProduct()); // update produk jika perlu

        return orderItemRepository.save(existingOrderItem);
    }

    public void deleteOrderItem(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}
