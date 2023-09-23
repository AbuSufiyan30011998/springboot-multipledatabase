package com.bs.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bs.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
