package kr.megaptera.makaobank.dtos;

import java.util.Objects;

public class ProductDto {
    private Long id;
    private String name;
    private String maker;
    private Long price;
    private String description;
    private String imageUrl;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String maker, Long price, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.price = price;
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

    @Override
    public String toString() {
        return "ProductDto(" +
                "id:" + id + ", " +
                "name:" + name + ", " +
                "price:" + price + ", " +
                "maker:" + maker + ", " +
                "description:" + description + ", " +
                "imageUrl:" + imageUrl + ")";
    }

    @Override
    public boolean equals(Object other) {
        ProductDto otherProductDto = (ProductDto) other;

        return Objects.equals(id, otherProductDto.id) &&
                Objects.equals(name, otherProductDto.name) &&
                Objects.equals(price, otherProductDto.price) &&
                Objects.equals(maker, otherProductDto.maker) &&
                Objects.equals(description, otherProductDto.description) &&
                Objects.equals(imageUrl, otherProductDto.imageUrl);
    }
}
