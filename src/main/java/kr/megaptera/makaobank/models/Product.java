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

    String name;

    Long price;

    String maker;

    String description;

    String imageUrl;

    public Product() {
    }

    public Product(Long id, String name, Long price, String maker, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.maker = maker;
        this.description = description;
        this.imageUrl = imageUrl;
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
        return new ProductDto(id, name, price, maker, description, imageUrl);
    }
}
