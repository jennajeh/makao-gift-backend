package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.OrdersDto;
import kr.megaptera.makaobank.models.Order;
import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.repositories.OrderRepository;
import kr.megaptera.makaobank.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetOrdersServiceTest {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private GetOrdersService getOrdersService;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        orderRepository = mock(OrderRepository.class);
        getOrdersService = new GetOrdersService(orderRepository, productRepository);
    }

    @Test
    void getOrders() {
        given(productRepository.findById(any()))
                .willReturn(Optional.of(Product.fake()));

        given(orderRepository.findAllByUserId(any(), any()))
                .willReturn(new PageImpl<>(List.of(Order.fake())));

        Long userId = 1L;
        Integer page = 1;
        Integer size = 8;

        OrdersDto ordersDto = getOrdersService.orders(userId, page, size);

        assertThat(ordersDto).isNotNull();
        assertThat(ordersDto.getOrders().get(0).getProduct().getName())
                .isEqualTo("소파");
    }
}
