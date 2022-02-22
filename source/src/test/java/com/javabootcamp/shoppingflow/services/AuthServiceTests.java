package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.models.User;
import com.javabootcamp.shoppingflow.repositories.UserRepository;
import com.javabootcamp.shoppingflow.utils.BCryptUtil;
import com.javabootcamp.shoppingflow.views.auth.AuthRequest;
import com.javabootcamp.shoppingflow.views.auth.AuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {

    private AuthService authServiceTest;

    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setup() {
        lenient().when(userRepositoryMock.existsByUsername(Mockito.anyString())).thenReturn(true);
        lenient().when(userRepositoryMock.findByUsername(Mockito.anyString())).thenReturn(createUserMock());

        authServiceTest = new AuthService(userRepositoryMock);
    }

    @Test
    void signInShouldBeSuccess() {
        // Arrange
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("test");
        authRequest.setPassword("test");

        // Act
        AuthResponse authResponse = authServiceTest.signIn(authRequest);

        // Assert
        assertNotNull(authResponse);
    }

    private Optional<User> createUserMock() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword(BCryptUtil.hashString("test"));
        return Optional.of(user);
    }
}
