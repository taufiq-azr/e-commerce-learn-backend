package com.azharstudios.e_commerce_learn_backend.repository;

import com.azharstudios.e_commerce_learn_backend.models.Category;
import com.azharstudios.e_commerce_learn_backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoryByProduct(Product product);
}
