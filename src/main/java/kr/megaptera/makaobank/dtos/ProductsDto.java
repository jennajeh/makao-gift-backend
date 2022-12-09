package kr.megaptera.makaobank.dtos;

import java.util.List;

public class ProductsDto {
    private List<ProductDto> products;
    private PagesDto pages;

    public ProductsDto(List<ProductDto> products, PagesDto pagesDto) {
        this.products = products;
        this.pages = pagesDto;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public PagesDto getPages() {
        return pages;
    }
}
