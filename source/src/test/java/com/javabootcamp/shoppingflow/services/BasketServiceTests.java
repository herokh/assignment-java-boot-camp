package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.ApplicationContext;
import com.javabootcamp.shoppingflow.mocks.BasketDataMock;
import com.javabootcamp.shoppingflow.mocks.ProductDataMock;
import com.javabootcamp.shoppingflow.mocks.UserDataMock;
import com.javabootcamp.shoppingflow.models.Basket;
import com.javabootcamp.shoppingflow.models.BasketProduct;
import com.javabootcamp.shoppingflow.models.Product;
import com.javabootcamp.shoppingflow.models.User;
import com.javabootcamp.shoppingflow.repositories.BasketProductRepository;
import com.javabootcamp.shoppingflow.repositories.BasketRepository;
import com.javabootcamp.shoppingflow.repositories.ProductRepository;
import com.javabootcamp.shoppingflow.views.basket.BasketResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTests {

    @Mock
    private BasketRepository basketRepositoryMock;

    @Mock
    private BasketProductRepository basketProductRepositoryMock;

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private ApplicationContext applicationContextMock;

    private BasketService basketServiceTest;

    @BeforeEach
    void setup () {
        lenient().when(applicationContextMock.getCurrentUser()).thenReturn(UserDataMock.createUserMock());

        basketServiceTest = new BasketService(basketRepositoryMock,
                basketProductRepositoryMock,
                productRepositoryMock,
                applicationContextMock);
    }

    @Test
    void updateUserBasketShouldBeSuccess() {
        // Arrange
        when(productRepositoryMock.findById(Mockito.anyLong())).thenReturn(createProductMock());
        when(basketRepositoryMock.findByUser(Mockito.any(User.class))).thenReturn(createUserBasketMock());

        // Act
        assertDoesNotThrow(() -> basketServiceTest.updateUserBasket(1, 1));

        // Assert
        verify(basketProductRepositoryMock, times(1)).save(Mockito.any(BasketProduct.class));
    }

    @Test
    void getUserBasketShouldBeSuccess() {
        // Arrange
        when(basketRepositoryMock.findByUser(Mockito.any(User.class))).thenReturn(createUserBasketMock());

        // Act
        BasketResponse userBasket = assertDoesNotThrow(() ->
                basketServiceTest.getUserBasket());

        // Assert
        assertNotNull(userBasket);
    }

    @Test
    void cleanupUserBasketShouldBeSuccess() {
        // Arrange
        when(basketRepositoryMock.findByUser(Mockito.any(User.class))).thenReturn(createUserBasketMock());

        // Act
        assertDoesNotThrow(() -> basketServiceTest.cleanupUserBasket());

        // Assert
        verify(basketRepositoryMock, times(1)).delete(Mockito.any(Basket.class));
        verify(basketProductRepositoryMock, times(1)).delete(Mockito.any(BasketProduct.class));
    }

    private Optional<Basket> createUserBasketMock() {
        var basket = BasketDataMock.createUserBasketMock();
        return Optional.of(basket);
    }

    private Optional<Product> createProductMock() {
        Product product = ProductDataMock.getProduct();
        return Optional.of(product);
    }
}
