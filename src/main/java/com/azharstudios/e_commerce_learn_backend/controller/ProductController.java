package com.azharstudios.e_commerce_learn_backend.controller;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.Product;
import com.azharstudios.e_commerce_learn_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Mendapatkan semua produk
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
        List<Product> products = productService.findAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Mendapatkan produk berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId) {
        try {
        Product product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Mendapatkan semua produk berdasarkan kategori
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long categoryId) {
        try {
        List<Product> products = productService.findAllProductByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Mendapatkan semua produk berdasarkan ID review
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<Product>> getProductsByReviewId(@PathVariable Long reviewId) {
        try {
        List<Product> products = productService.findAllProductByReviewId(reviewId);
        return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Mendapatkan semua produk berdasarkan ID order
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<Product>> getProductsByOrderId(@PathVariable Long orderId) {
        try {
        List<Product> products = productService.findAllProductByOrderId(orderId);
        return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Mendapatkan semua produk berdasarkan ID shop
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<Product>> getProductsByShopId(@PathVariable Long shopId) {
        try {
        List<Product> products = productService.findAllProductByShopId(shopId);
        return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Membuat produk baru
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Mengupdate produk
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long productId, @RequestBody Product productUpdated) {
        try {
        Product updatedProduct = productService.updateProduct(productId, productUpdated);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Menghapus produk
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e){
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    }
}
