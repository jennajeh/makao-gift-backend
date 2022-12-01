package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.ProductDto;
import kr.megaptera.makaobank.dtos.ProductsDto;
import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ProductsDto list() {
        List<ProductDto> productDtos = productService.list()
                .stream()
                .map(Product::toDto)
                .collect(Collectors.toList());

        return new ProductsDto(productDtos);
    }
}
