package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.ApplicationContext;
import com.javabootcamp.shoppingflow.exceptions.ProductNotFoundException;
import com.javabootcamp.shoppingflow.models.Basket;
import com.javabootcamp.shoppingflow.models.BasketProduct;
import com.javabootcamp.shoppingflow.models.Product;
import com.javabootcamp.shoppingflow.models.User;
import com.javabootcamp.shoppingflow.repositories.BasketProductRepository;
import com.javabootcamp.shoppingflow.repositories.BasketRepository;
import com.javabootcamp.shoppingflow.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Transactional
    public void updateToBasket(long productId, int quantity) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            User currentUser = applicationContext.getCurrentUser();
            Optional<Basket> userBasket = basketRepository.findByUser(currentUser);
            Basket basket = userBasket.isPresent() ? userBasket.get() : new Basket();
            var basketProducts = basket.getBasketProducts();
            if (basketProducts == null) {
                basketProducts = new ArrayList<>();
            }
            BasketProduct basketProduct = basketProducts.stream()
                    .filter(x -> productId == x.getProduct().getId())
                    .findAny()
                    .orElse(null);
            if (basketProduct == null) {
                basketProduct = new BasketProduct();
                basketProduct.setProduct(product.get());
                basketProduct.setQuantity(quantity);
                basketProduct.setBasket(basket);
                basketProducts.add(basketProduct);
            }
            else {
                basketProduct.setQuantity(basketProduct.getQuantity() + quantity);
            }
            basket.setBasketProducts(basketProducts);
            basket.setUser(currentUser);

            basketRepository.save(basket);
        }
        else {
            throw new ProductNotFoundException(String.format("Product %d not found", productId));
        }
    }

}
