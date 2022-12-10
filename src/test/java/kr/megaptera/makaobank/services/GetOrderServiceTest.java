package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.OrderDto;
import kr.megaptera.makaobank.exceptions.InvalidUser;
import kr.megaptera.makaobank.exceptions.OrderNotFound;
import kr.megaptera.makaobank.models.Order;
import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.repositories.OrderRepository;
import kr.megaptera.makaobank.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetOrderServiceTest {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private GetOrderService getOrderService;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        productRepository = mock(ProductRepository.class);
        getOrderService = new GetOrderService(orderRepository, productRepository);
    }

    @Test
    void getOrder() {
        Long userId = 1L;
        Long id = 1L;

        given(productRepository.findById(id))
                .willReturn(Optional.of(Product.fake()));

        given(orderRepository.findById(id))
                .willReturn(Optional.of(Order.fake()));

        OrderDto orderDto = getOrderService.order(userId, id);

        assertThat(orderDto.getId()).isEqualTo(id);
    }

    @Test
    void getOrderOfOtherUser() {
        Long id = 1L;

        given(orderRepository.findById(id))
                .willReturn(Optional.of(Order.fake()));

        assertThrows(InvalidUser.class, () -> getOrderService.order(Order.fake().userId() + 1, id));
    }

    @Test
    void orderNotFound() {
        Long userId = 1L;
        Long id = 1L;

        assertThrows(OrderNotFound.class, () -> getOrderService.order(userId, id));
    }
}
