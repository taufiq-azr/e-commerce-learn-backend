package com.azharstudios.e_commerce_learn_backend.repository;

import com.azharstudios.e_commerce_learn_backend.models.Order;
import com.azharstudios.e_commerce_learn_backend.models.Product;
import com.azharstudios.e_commerce_learn_backend.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o JOIN o.orderItems oi WHERE oi.product.id = :productId")
    List<Order> findOrderByProductId(@Param("productId") Long productId);

    @Query("SELECT o FROM Order o WHERE o.review.id = :reviewId")
    List<Order> findOrderByReviewId(@Param("reviewId") Long reviewId);

    @Query("SELECT CASE WHEN COUNT(oi) > 0 THEN true ELSE false END FROM Order o JOIN o.orderItems oi WHERE oi.product.id = :productId")
    boolean existsByProductId(@Param("productId") Long productId);
}
