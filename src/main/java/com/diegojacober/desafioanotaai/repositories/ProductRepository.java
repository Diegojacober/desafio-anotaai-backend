package com.diegojacober.desafioanotaai.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diegojacober.desafioanotaai.domain.product.Product;

public interface ProductRepository extends MongoRepository<Product, String>{
    
}
