package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.ProductDto;
import kr.megaptera.makaobank.exceptions.ProductNotFound;
import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetProductService {
    private ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto detail(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound(id));

        return product.toDto();
    }
}
