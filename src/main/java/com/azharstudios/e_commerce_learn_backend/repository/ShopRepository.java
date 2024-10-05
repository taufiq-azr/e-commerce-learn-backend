package com.azharstudios.e_commerce_learn_backend.repository;

import com.azharstudios.e_commerce_learn_backend.models.Product;
import com.azharstudios.e_commerce_learn_backend.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findShopByProductId(Long productId);
}
