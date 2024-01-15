package com.diegojacober.desafioanotaai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegojacober.desafioanotaai.domain.category.Category;
import com.diegojacober.desafioanotaai.domain.category.CategoryDTO;
import com.diegojacober.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.diegojacober.desafioanotaai.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category insert(CategoryDTO categoryData) {
        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);
        return newCategory;
    }

    public List<Category> getAll() {
        return this.repository.findAll();
    }

    public Category update(String id, CategoryDTO categoryData) {
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if (!categoryData.title().isEmpty())
            category.setTitle(categoryData.title());
        if (!categoryData.description().isEmpty())
            category.setDescription(categoryData.description());

        this.repository.save(category);

        return category;
    }

    public void delete(String id) {
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(category);
    }

    public Optional<Category> getById(String id) {
        return this.repository.findById(id);
    }
}
