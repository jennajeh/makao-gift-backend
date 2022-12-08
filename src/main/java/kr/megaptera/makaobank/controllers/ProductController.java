package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.PageMetadataDto;
import kr.megaptera.makaobank.dtos.ProductDto;
import kr.megaptera.makaobank.dtos.ProductsDto;
import kr.megaptera.makaobank.exceptions.ProductNotFound;
import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.services.GetProductService;
import kr.megaptera.makaobank.services.GetProductsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public ProductsDto list(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size
    ) {
        Page<Product> products = getProductsService.getProducts(page, size);

        List<ProductDto> productDtos = products
                .stream()
                .map(Product::toDto)
                .collect(Collectors.toList());

        return new ProductsDto(productDtos, new PageMetadataDto(products.getTotalPages()));
    }

    @GetMapping("/{id}")
    public ProductDto detail(@PathVariable Long id) {
        return getProductService.detail(id);

    }

    @ExceptionHandler(ProductNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String productNotFound() {
        return "Product not found";
    }
}
