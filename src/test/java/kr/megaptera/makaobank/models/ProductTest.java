package kr.megaptera.makaobank.models;

import kr.megaptera.makaobank.dtos.ProductDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void creation() {
        Product product = new Product(1L, "소파", "제조사", 30000L, "편안한 가구", "이미지");

        assertThat(product.id()).isEqualTo(1L);
        assertThat(product.name()).isEqualTo("소파");
        assertThat(product.maker()).isEqualTo("제조사");
        assertThat(product.price()).isEqualTo(30000L);
        assertThat(product.description()).isEqualTo("편안한 가구");
        assertThat(product.imageUrl()).isEqualTo("이미지");
    }

    @Test
    void toDto() {
        Product product = new Product(1L, "소파", "제조사", 30000L, "편안한 가구", "이미지");

        ProductDto productDto = product.toDto();

        assertThat(productDto).isEqualTo(new ProductDto(1L, "소파", "제조사", 30000L, "편안한 가구", "이미지"));
    }
}
