package com.javabootcamp.shoppingflow;

import com.javabootcamp.shoppingflow.seeders.UserSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ShoppingFlowApplication {

	@Autowired
	private UserSeeder userSeeder;

	@PostConstruct
	public void initialData() {
		userSeeder.createUserMock();
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingFlowApplication.class, args);
	}

}
