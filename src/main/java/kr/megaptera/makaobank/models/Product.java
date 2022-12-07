package kr.megaptera.makaobank.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.megaptera.makaobank.dtos.ProductDto;

@Entity
public class Product {
    @Id
    @GeneratedValue
    Long id;

    Long price;

    String name;

    String maker;

    String description;

    String imageUrl;

    public Product() {
    }

    public Product(Long id, String name, String maker, Long price, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public static Product fake() {
        return new Product(1L, "이름", "제조사", 3000L, "설명", "이미지");
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getMaker() {
        return maker;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductDto toDto() {
        return new ProductDto(id, name, maker, price, description, imageUrl);
    }
}
