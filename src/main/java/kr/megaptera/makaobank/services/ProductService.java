package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> list() {
        List<Product> products = productRepository.findAll();

        return products;
    }
}
