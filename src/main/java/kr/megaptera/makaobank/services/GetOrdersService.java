package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.OrderDto;
import kr.megaptera.makaobank.dtos.OrdersDto;
import kr.megaptera.makaobank.dtos.PageMetadataDto;
import kr.megaptera.makaobank.exceptions.ProductNotFound;
import kr.megaptera.makaobank.models.Order;
import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.repositories.OrderRepository;
import kr.megaptera.makaobank.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetOrdersService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public GetOrdersService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrdersDto orders(Long userId, Integer page, Integer size) {
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Order> orders = orderRepository.findAllByUserId(userId, pageable);

        List<OrderDto> orderDtos = orders.getContent().stream()
                .map(order -> {
                    Product product = productRepository.findById(order.productId())
                            .orElseThrow(() -> new ProductNotFound(order.productId()));

                    return new OrderDto(order.id(), order.quantity(), order.totalPrice(product.price()),
                            order.receiver(), order.address(), order.message(), product.toDto(), order.createdAt());
                })
                .collect(Collectors.toList());

        return new OrdersDto(orderDtos, new PageMetadataDto(orders.getTotalPages()));
    }
}
