package com.azharstudios.e_commerce_learn_backend.repository;

import com.azharstudios.e_commerce_learn_backend.models.Order;
import com.azharstudios.e_commerce_learn_backend.models.Product;
import com.azharstudios.e_commerce_learn_backend.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByProductId(Long productId);

    List<Order> findOrderByReviewId(Long reviewId);

    boolean existsByProductId(Long productId);
}
