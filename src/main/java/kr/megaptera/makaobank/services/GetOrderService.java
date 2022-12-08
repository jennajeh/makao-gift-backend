package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.OrderDto;
import kr.megaptera.makaobank.exceptions.InvalidUser;
import kr.megaptera.makaobank.exceptions.OrderNotFound;
import kr.megaptera.makaobank.exceptions.ProductNotFound;
import kr.megaptera.makaobank.models.Order;
import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.repositories.OrderRepository;
import kr.megaptera.makaobank.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetOrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public GetOrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderDto order(Long userId, Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFound(id));

        if (order.userId() != userId) {
            throw new InvalidUser();
        }

        Product product = productRepository.findById(order.productId())
                .orElseThrow(() -> new ProductNotFound(order.productId()));

        return new OrderDto(order.id(), order.quantity(), order.totalPrice(product.price()),
                order.receiver(), order.address(), order.message(),
                product.toDto(), order.createdAt());
    }
}
