package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.OrderRequestDto;
import kr.megaptera.makaobank.exceptions.OrderFailed;
import kr.megaptera.makaobank.models.Order;
import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.models.User;
import kr.megaptera.makaobank.repositories.OrderRepository;
import kr.megaptera.makaobank.repositories.ProductRepository;
import kr.megaptera.makaobank.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateOrderService {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public CreateOrderService(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Order order(Long userId, OrderRequestDto orderRequestDto) {
        Long productId = orderRequestDto.getProductId();
        Integer quantity = orderRequestDto.getQuantity();
        String receiver = orderRequestDto.getReceiver();
        String address = orderRequestDto.getAddress();
        String message = orderRequestDto.getMessage();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new OrderFailed());

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new OrderFailed());

        user.order(product, quantity);

        Long totalPrice = product.price() * quantity;

        Order order = new Order(null, userId, productId, quantity, totalPrice, receiver, address, message);

        orderRepository.save(order);

        return order;
    }
}
