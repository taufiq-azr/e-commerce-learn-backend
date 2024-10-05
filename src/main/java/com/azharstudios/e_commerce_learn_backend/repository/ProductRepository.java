package com.azharstudios.e_commerce_learn_backend.repository;

import com.azharstudios.e_commerce_learn_backend.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllProductByCategoryId(Long categoryId);

    List<Product> findAllProductByReviewId(Long reviewId);

    List<Product> findAllProductByOrderId(Long orderId);

    List<Product> findAllProductByShopId(Long shopId);
}
