package com.azharstudios.e_commerce_learn_backend.controller;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.Review;
import com.azharstudios.e_commerce_learn_backend.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        try {
            List<Review> reviews = reviewService.findAllReview();
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id){
        try {
             Review review = reviewService.findReviewById(id);
             return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Mendapatkan ulasan berdasarkan ID produk
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productId) {
        try {
        List<Review> reviews = reviewService.findReviewByProductId(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<Review> createReview(@PathVariable Long orderId, @RequestBody Review review) {
        try {
        Review createdReview = reviewService.createReview(orderId, review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Mengupdate ulasan
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        try {
        Review updatedReview = reviewService.updateReview(id, review);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        }  catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Menghapus ulasan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        try {
            reviewService.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
