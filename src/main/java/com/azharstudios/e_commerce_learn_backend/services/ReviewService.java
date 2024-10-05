package com.azharstudios.e_commerce_learn_backend.services;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.Order;
import com.azharstudios.e_commerce_learn_backend.models.Review;
import com.azharstudios.e_commerce_learn_backend.models.enums.OrderStatus;
import com.azharstudios.e_commerce_learn_backend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderService orderService;  // Tambahkan OrderService

    public List<Review> findAllReview() {
        return reviewRepository.findAll();
    }

    public Review findReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(
                () -> new NotFoundException("Review Not Found !"));
    }

    public List<Review> findReviewByProductId(Long productId) {
        return reviewRepository.findReviewByProductId(productId);
    }

    public List<Review> findReviewByOrderId(Long orderId) {
        return reviewRepository.findReviewByOrderId(orderId);
    }

    public Review createReview(Long orderId, Review review) {
        Order order = orderService.findOrderById(orderId);  // Ambil Order berdasarkan ID

        // Pastikan status order adalah 'selesai'
        if (!order.getStatus().equals(OrderStatus.COMPLETED)) {
            throw new IllegalArgumentException("Order status must be 'COMPLETED' to create a review.");
        }

        // Pastikan produk yang direview adalah produk dari order
        if (!order.getOrderItems().stream().anyMatch(
                item -> item.getProduct().equals(review.getProduct()))) {
            throw new IllegalArgumentException("You can only review products you have ordered.");
        }

        review.setOrder(order);  // Set order ke review
        return reviewRepository.save(review);  // Simpan review
    }

    public Review updateReview(Long reviewId, Review reviewUpdated) {
        Review reviewExist = reviewRepository.findById(reviewId).orElseThrow(
                () -> new NotFoundException("Review Not Found, with Id " + reviewId));
        reviewExist.setReview(reviewUpdated.getReview());
        reviewExist.setProduct(reviewUpdated.getProduct());

        return reviewRepository.save(reviewExist);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
