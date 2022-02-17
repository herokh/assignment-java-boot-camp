package com.javabootcamp.shoppingflow;

import com.javabootcamp.shoppingflow.models.User;
import com.javabootcamp.shoppingflow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ShoppingFlowApplication {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void initialData() {
		this.createUserMock();
	}

	@Transactional
	private void createUserMock() {
		var user1 = new User();
		user1.setUsername("hero");
		user1.setPassword("1234");
		userRepository.save(user1);
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingFlowApplication.class, args);
	}

}
