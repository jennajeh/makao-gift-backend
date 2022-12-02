package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.ProductDto;
import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetProductServiceTest {
    private GetProductService getProductService;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        getProductService = new GetProductService(productRepository);
    }

    @Test
    void detail() {
        given(productRepository.findById(any()))
                .willReturn(Optional.of(Product.fake()));

        ProductDto productDto = getProductService.detail(1L);

        verify(productRepository).findById(1L);

        assertThat(productDto.getMaker()).isEqualTo("제조사");
    }
}
