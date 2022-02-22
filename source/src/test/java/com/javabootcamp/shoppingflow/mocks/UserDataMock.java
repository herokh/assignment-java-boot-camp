package com.javabootcamp.shoppingflow.mocks;

import com.javabootcamp.shoppingflow.models.Address;
import com.javabootcamp.shoppingflow.models.User;
import com.javabootcamp.shoppingflow.utils.BCryptUtil;

import java.util.ArrayList;

public class UserDataMock {

    public static User createUserMock() {
        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setUsername("test");
        user.setPassword(BCryptUtil.hashString("test"));
        user.setAddress(new Address());
        user.setOrders(new ArrayList<>());
        return user;
    }

}
