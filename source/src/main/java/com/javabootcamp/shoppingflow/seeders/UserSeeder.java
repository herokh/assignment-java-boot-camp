package com.javabootcamp.shoppingflow.seeders;

import com.javabootcamp.shoppingflow.models.User;
import com.javabootcamp.shoppingflow.repositories.UserRepository;
import com.javabootcamp.shoppingflow.utils.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder {

    @Autowired
    private UserRepository userRepository;

    public void createUserMock() {
        String hashPassword = BCryptUtil.hashString("1234");
        var user1 = new User();
        user1.setUsername("hero");
        user1.setPassword(hashPassword);
        userRepository.save(user1);
    }

}
