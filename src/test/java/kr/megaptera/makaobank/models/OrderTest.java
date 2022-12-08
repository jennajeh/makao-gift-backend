package kr.megaptera.makaobank.models;

import kr.megaptera.makaobank.dtos.OrderCreateDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void toDto() {
        Order order = new Order(1L, 1L, 1, 10_000L, "강보니", "서울시 성동구 성수동", "생일 축하해!");

        OrderCreateDto orderCreateDto = order.toCreateDto();

        assertThat(orderCreateDto).isEqualTo(new OrderCreateDto(1L, 1L, 1));
    }

}
