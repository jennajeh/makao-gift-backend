package kr.megaptera.makaobank.dtos;

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
}
