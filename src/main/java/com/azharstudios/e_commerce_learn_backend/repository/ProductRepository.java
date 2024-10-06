package com.azharstudios.e_commerce_learn_backend.repository;

import com.azharstudios.e_commerce_learn_backend.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory_CategoryId(Long categoryId); // Ubah ke findByCategoryId

    List<Product> findByReviews_ReviewId(Long reviewId); // Ubah ke findByReviewId

    List<Product> findByOrderItems_Order_orderId(Long orderId); // Ubah ke findByOrderId

    List<Product> findByShop_ShopId(Long shopId);
}
