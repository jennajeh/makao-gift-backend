package kr.megaptera.makaobank.dtos;

public class ProductDto {
    private Long id;
    private String name;
    private Long price;
    private String maker;
    private String description;
    private String imageUrl;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, Long price, String maker, String description, String imageUrl) {
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
}
