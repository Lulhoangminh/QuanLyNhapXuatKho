package com.example.bill_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.bill_management.entities") // Entity package
@EnableJpaRepositories(basePackages = "com.example.bill_management.repositories")
public class BillManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillManagementApplication.class, args);
	}

}
