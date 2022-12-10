package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductsServiceTest {
    private GetProductsService getProductsService;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        getProductsService = new GetProductsService(productRepository);
    }

    @Test
    void list() {
        Product product = mock(Product.class);

        given(productRepository.findAll(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(product)));

        Integer page = 1;
        Integer size = 12;

        assertThat(getProductsService.list(page, size)).hasSize(1);
    }
}
