package com.bs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bs.model.customer.Customer;
import com.bs.model.product.Product;
import com.bs.repository.customer.CustomerRepository;
import com.bs.repository.product.ProductRepository;

@SpringBootApplication
public class SpringbootMultipledatabaseApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMultipledatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		productRepository.saveAll(Arrays.asList(new Product(10,"P1","BOOK"),
				
				new Product(11,"P2","KEYWORD"),
				new Product(12,"P3","MOBILE")
				));
		
		customerRepository.saveAll(Arrays.asList(new Customer(121,"SAM","sam@gmail.com"),
				new Customer(122,"SYED","syed@gmail.com"),
				new Customer(123,"RAM","ram@gmail.com"),
				new Customer(124,"SYAM","syam@gmail.com")
				));

	}

}
