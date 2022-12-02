package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

        given(productRepository.findAll()).willReturn(List.of(product));

        List<Product> products = getProductsService.list();

        assertThat(products).hasSize(1);
    }
}
