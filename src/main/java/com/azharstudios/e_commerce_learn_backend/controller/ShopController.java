package com.azharstudios.e_commerce_learn_backend.controller;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.Shop;
import com.azharstudios.e_commerce_learn_backend.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shops")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService){
        this.shopService = shopService;
    }

    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops(){
       try {
           List<Shop> shops = shopService.findAllShop();
           return new ResponseEntity<>(shops, HttpStatus.OK);
       } catch (NotFoundException e){
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable Long id){
        try {
            Shop shop = shopService.findShopById(id);
            return new ResponseEntity<>(shop, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Shop>> getShopsByProductId(@PathVariable Long productId) {
        try {
            List<Shop> shops = shopService.findShopByProductId(productId);
            return new ResponseEntity<>(shops, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        Shop newShop = shopService.createShop(shop);
        return new ResponseEntity<>(newShop, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable Long id, @RequestBody Shop shopUpdated) {
        try {
            Shop updatedShop = shopService.updateShop(id, shopUpdated);
            return new ResponseEntity<>(updatedShop, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        try {
            shopService.deleteShop(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
