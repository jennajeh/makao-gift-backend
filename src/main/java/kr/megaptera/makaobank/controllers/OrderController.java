package kr.megaptera.makaobank.controllers;

import jakarta.validation.Valid;
import kr.megaptera.makaobank.dtos.OrderCreateDto;
import kr.megaptera.makaobank.dtos.OrderRequestDto;
import kr.megaptera.makaobank.exceptions.OrderFailed;
import kr.megaptera.makaobank.exceptions.OrderNotFound;
import kr.megaptera.makaobank.models.Order;
import kr.megaptera.makaobank.services.CreateOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private CreateOrderService createOrderService;

    public OrderController(CreateOrderService createOrderService) {
        this.createOrderService = createOrderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderCreateDto order(
            @RequestAttribute Long userId,
            @Valid @RequestBody OrderRequestDto orderRequestDto
    ) {
        Order order = createOrderService.order(userId, orderRequestDto);

        return order.toCreateDto();
    }

    @ExceptionHandler(OrderFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String orderFailed() {
        return "Order failed!";
    }

    @ExceptionHandler(OrderNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String orderNotFound() {
        return "Order not found!";
    }
}
