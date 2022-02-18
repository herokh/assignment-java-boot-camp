package com.javabootcamp.shoppingflow;

import com.javabootcamp.shoppingflow.models.User;
import lombok.Getter;

public class ApplicationContext {
    private @Getter User currentUser;

    public void setCurrentUserIfNotExists(User currentUser) {
        if (this.currentUser == null) {
            this.currentUser = currentUser;
        }
    }

}
