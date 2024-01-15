package com.diegojacober.desafioanotaai.domain.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.diegojacober.desafioanotaai.domain.category.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    
    @Id
    private String id;
    
    private String title;
    
    private String description;
    
    private String ownerId;

    private Integer price;
    // sempre multiplca por 100 na hora de salvar para tirar as virgulas
    // sempre divide por 100 na hora de ler

    private Category category;

    public Product(ProductDTO productDTO) {
        this.title = productDTO.title();
        this.description = productDTO.description();
        this.price = productDTO.price();
        this.ownerId = productDTO.ownerId();
    }
}
