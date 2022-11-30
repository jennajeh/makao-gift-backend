package kr.megaptera.makaobank.dtos;

import java.util.List;

public class ProductsDto {
    private List<ProductDto> productDto;

    public ProductsDto(List<ProductDto> productDto) {
        this.productDto = productDto;
    }

    public List<ProductDto> getProductDto() {
        return productDto;
    }
}
