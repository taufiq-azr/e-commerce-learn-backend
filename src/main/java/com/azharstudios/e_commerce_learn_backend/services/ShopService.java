package com.azharstudios.e_commerce_learn_backend.services;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.Product;
import com.azharstudios.e_commerce_learn_backend.models.Shop;
import com.azharstudios.e_commerce_learn_backend.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public List<Shop> findAllShop(){
        return shopRepository.findAll();
    }

    public Shop findShopById(Long shopId){
        return shopRepository.findById(shopId).orElseThrow(
                () -> new NotFoundException("Shop Not Found !"));
    }
    public List<Shop> findShopByProductId(Long productId){
        return shopRepository.findShopByProductId(productId);
    }

    public Shop createShop(Shop shop){
        return shopRepository.save(shop);
    }

    public Shop updateShop(Long shopId, Shop shopUpdated){
        Shop shopExist = shopRepository.findById(shopId).orElseThrow(
                () -> new NotFoundException("Shop Not Found, with Id "+ shopId));
        shopExist.setShopName(shopUpdated.getShopName());
        shopExist.setProducts(shopUpdated.getProducts());

        return shopRepository.save(shopExist);
    }

    public void deleteShop(Long shopId){
        shopRepository.deleteById(shopId);
    }
}
