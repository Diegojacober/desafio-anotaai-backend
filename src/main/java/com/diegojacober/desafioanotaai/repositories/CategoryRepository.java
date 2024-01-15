package com.diegojacober.desafioanotaai.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diegojacober.desafioanotaai.domain.category.Category;

public interface CategoryRepository extends MongoRepository<Category, String>{
    
}
