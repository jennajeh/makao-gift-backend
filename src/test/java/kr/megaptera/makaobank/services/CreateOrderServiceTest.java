package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.OrderRequestDto;
import kr.megaptera.makaobank.exceptions.OrderFailed;
import kr.megaptera.makaobank.models.Order;
import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.models.User;
import kr.megaptera.makaobank.repositories.OrderRepository;
import kr.megaptera.makaobank.repositories.ProductRepository;
import kr.megaptera.makaobank.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateOrderServiceTest {
    private CreateOrderService createOrderService;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private OrderRequestDto orderRequestDto;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        productRepository = mock(ProductRepository.class);
        orderRepository = mock(OrderRepository.class);
        createOrderService = new CreateOrderService(userRepository, productRepository, orderRepository);
        orderRequestDto = new OrderRequestDto(
                1L, 1, "강보니", "서울시 성동구 성수동", "생일 축하해!");

        given(productRepository.findById(any()))
                .willReturn(Optional.of(Product.fake()));
    }

    @Test
    void orderSuccess() {
        User user = User.fake();
        Long initialAmount = user.amount();

        given(userRepository.findById(any())).willReturn(Optional.of(user));

        Product product = Product.fake();

        given(productRepository.findById(any())).willReturn(Optional.of(product));

        Long userId = 1L;

        Order order = createOrderService.order(userId, orderRequestDto);

        assertThat(order).isNotNull();
        assertThat(user.amount())
                .isEqualTo(initialAmount - product.price() * orderRequestDto.getQuantity());

        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void orderWithNotFoundUser() {
        Long userId = 1L;

        assertThrows(OrderFailed.class, () -> {
            createOrderService.order(userId, orderRequestDto);
        });
    }

    @Test
    void orderWithNotFoundProduce() {
        Long userId = 1L;

        assertThrows(OrderFailed.class, () -> {
            createOrderService.order(userId, orderRequestDto);
        });
    }
}
