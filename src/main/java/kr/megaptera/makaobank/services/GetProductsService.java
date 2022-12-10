package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetProductsService {
    private ProductRepository productRepository;

    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> list(Integer page, Integer size) {
        Sort sort = Sort.by("id");

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        return productRepository.findAll(pageable);
    }
}
