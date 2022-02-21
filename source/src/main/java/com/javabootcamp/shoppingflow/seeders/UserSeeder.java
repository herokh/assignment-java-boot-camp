package com.javabootcamp.shoppingflow.seeders;

import com.javabootcamp.shoppingflow.models.Address;
import com.javabootcamp.shoppingflow.models.User;
import com.javabootcamp.shoppingflow.repositories.UserRepository;
import com.javabootcamp.shoppingflow.utils.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder {

    @Autowired
    private AddressSeeder addressSeeder;

    @Autowired
    private UserRepository userRepository;

    public void createUserMock() {
        String hashPassword = BCryptUtil.hashString("1234");
        var mainUser = new User();
        mainUser.setUsername("hero");
        mainUser.setPassword(hashPassword);
        mainUser.setName("Hero");
        mainUser.setAddress(addressSeeder.getDefaultAddressMock());

        userRepository.save(mainUser);
    }

}
