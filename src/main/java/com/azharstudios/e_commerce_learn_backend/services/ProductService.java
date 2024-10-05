package com.azharstudios.e_commerce_learn_backend.services;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.*;
import com.azharstudios.e_commerce_learn_backend.repository.CategoryRepository;
import com.azharstudios.e_commerce_learn_backend.repository.OrderRepository;
import com.azharstudios.e_commerce_learn_backend.repository.ProductRepository;
import com.azharstudios.e_commerce_learn_backend.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;
    private final OrderRepository orderRepository;

    // Constructor-based injection for better testability
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ShopRepository shopRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.shopRepository = shopRepository;
        this.orderRepository = orderRepository;
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product Not Found!"));
    }

    public List<Product> findAllProductByCategoryId(Long categoryId) {
        return productRepository.findAllProductByCategoryId(categoryId);
    }

    public List<Product> findAllProductByReviewId(Long reviewId) {
        return productRepository.findAllProductByReviewId(reviewId);
    }

    public List<Product> findAllProductByOrderId(Long orderId) {
        return productRepository.findAllProductByOrderId(orderId);
    }

    public List<Product> findAllProductByShopId(Long shopId) {
        return productRepository.findAllProductByShopId(shopId);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product productUpdated) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product Not Found, with Id " + productId));

        existingProduct.setProductName(productUpdated.getProductName());
        existingProduct.setDescription(productUpdated.getDescription());
        existingProduct.setPrice(productUpdated.getPrice());
        existingProduct.setUnit(productUpdated.getUnit());

        // Update Category
        if (productUpdated.getCategory() != null) {
            Category category = categoryRepository.findById(productUpdated.getCategory().getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found with id: " + productUpdated.getCategory().getCategoryId()));
            existingProduct.setCategory(category);
        }

        // Update Shop
        if (productUpdated.getShop() != null) {
            Shop shop = shopRepository.findById(productUpdated.getShop().getShopId())
                    .orElseThrow(() -> new NotFoundException("Shop not found with id: " + productUpdated.getShop().getShopId()));
            existingProduct.setShop(shop);
        }

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long productId) {
        // Check if the product is part of any existing orders
        if (orderRepository.existsByProductId(productId)) {
            throw new IllegalStateException("Cannot delete product that is part of existing orders.");
        }
        productRepository.deleteById(productId);
    }

}
