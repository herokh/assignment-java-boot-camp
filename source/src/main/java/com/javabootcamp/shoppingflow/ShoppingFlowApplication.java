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
	private UserSeeder userSeeder;

	@Autowired
	private AddressSeeder addressSeeder;

	@Autowired
	private MerchantSeeder merchantSeeder;

	@Autowired
	private ProductBrandSeeder productBrandSeeder;

	@Autowired
	private ProductCategorySeeder productCategorySeeder;

	@Autowired
	private ProductSeeder productSeeder;

	@PostConstruct
	public void initialData() {
		createDataMocks();
	}

	@Transactional
	private void createDataMocks() {
		addressSeeder.createAddressMock();
		userSeeder.createUserMock();
		merchantSeeder.createMerchantMock();
		productBrandSeeder.createProductBrandMock();
		productCategorySeeder.createProductCategoryMock();
		productSeeder.createProductsMock();
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingFlowApplication.class, args);
	}

}
