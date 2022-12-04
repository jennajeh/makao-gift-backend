package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.ProductDto;
import kr.megaptera.makaobank.dtos.ProductsDto;
import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetProductsService {
    private ProductRepository productRepository;

    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductsDto list() {
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtos =
                products.stream()
                        .map((Product::toDto))
                        .collect(Collectors.toList());

        return new ProductsDto(productDtos);
    }
}
