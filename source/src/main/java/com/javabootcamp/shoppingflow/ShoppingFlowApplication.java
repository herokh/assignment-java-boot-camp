package com.javabootcamp.shoppingflow;

import com.javabootcamp.shoppingflow.seeders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ShoppingFlowApplication {

	@Autowired
	private SeederContext seederContext;

	@PostConstruct
	public void initialData() {
		seederContext.createDataMocks();
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingFlowApplication.class, args);
	}

}
