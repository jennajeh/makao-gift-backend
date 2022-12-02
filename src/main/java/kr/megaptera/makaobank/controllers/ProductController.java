package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.ProductDto;
import kr.megaptera.makaobank.dtos.ProductsDto;
import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.services.GetProductService;
import kr.megaptera.makaobank.services.GetProductsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private GetProductService getProductService;
    private GetProductsService getProductsService;

    public ProductController(GetProductService getProductService, GetProductsService getProductsService) {
        this.getProductService = getProductService;
        this.getProductsService = getProductsService;
    }

    @GetMapping
    public ProductsDto list() {
        List<ProductDto> productDtos = getProductsService.list()
                .stream()
                .map(Product::toDto)
                .collect(Collectors.toList());

        return new ProductsDto(productDtos);
    }

    @GetMapping("/{id}")
    public ProductDto detail(@PathVariable Long id) {
        return getProductService.detail(id);

    }
}
