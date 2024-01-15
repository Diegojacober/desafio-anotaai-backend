package com.diegojacober.desafioanotaai.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegojacober.desafioanotaai.domain.category.Category;
import com.diegojacober.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.diegojacober.desafioanotaai.domain.product.Product;
import com.diegojacober.desafioanotaai.domain.product.ProductDTO;
import com.diegojacober.desafioanotaai.domain.product.exceptions.ProductNotFoundException;
import com.diegojacober.desafioanotaai.repositories.ProductRepository;
import com.diegojacober.desafioanotaai.services.aws.AwsSnsService;
import com.diegojacober.desafioanotaai.services.aws.MessageDTO;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AwsSnsService snsService;

    public Product insert(ProductDTO productData) {
        Category category = categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        this.repository.save(newProduct);
        snsService.publish(new MessageDTO(newProduct.getOwnerId()));
        return newProduct;
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

    public Product update(String id, ProductDTO productData) {
        Product product = repository.findById(id).orElseThrow(ProductNotFoundException::new);

        if (!productData.title().isEmpty())
            product.setTitle(productData.title());
        if (!productData.description().isEmpty())
            product.setDescription(productData.description());
        if (productData.price() != null)
            product.setPrice(productData.price());

        if (productData.categoryId() != null) {
            this.categoryService.getById(productData.categoryId())
                    .ifPresent(product::setCategory);
        }
        repository.save(product);

        snsService.publish(new MessageDTO(product.getOwnerId()));

        return product;
    }

    public void delete(String id) {
        Product product = repository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);
    }
}
