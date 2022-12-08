package kr.megaptera.makaobank.controllers;

import jakarta.validation.Valid;
import kr.megaptera.makaobank.dtos.OrderCreateDto;
import kr.megaptera.makaobank.dtos.OrderDto;
import kr.megaptera.makaobank.dtos.OrderRequestDto;
import kr.megaptera.makaobank.dtos.OrdersDto;
import kr.megaptera.makaobank.exceptions.InvalidUser;
import kr.megaptera.makaobank.exceptions.OrderFailed;
import kr.megaptera.makaobank.exceptions.OrderNotFound;
import kr.megaptera.makaobank.models.Order;
import kr.megaptera.makaobank.services.CreateOrderService;
import kr.megaptera.makaobank.services.GetOrderService;
import kr.megaptera.makaobank.services.GetOrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private CreateOrderService createOrderService;
    private GetOrdersService getOrdersService;
    private GetOrderService getOrderService;

    public OrderController(CreateOrderService createOrderService, GetOrdersService getOrdersService, GetOrderService getOrderService) {
        this.createOrderService = createOrderService;
        this.getOrdersService = getOrdersService;
        this.getOrderService = getOrderService;
    }

    @GetMapping
    public OrdersDto list(
            @RequestAttribute Long userId,
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size
    ) {
        return getOrdersService.orders(userId, page, size);
    }

    @GetMapping("{id}")
    public OrderDto detail(
            @RequestAttribute Long userId, @PathVariable Long id
    ) {
        return getOrderService.order(userId, id);
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

    @ExceptionHandler(InvalidUser.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidUser() {
        return "Invalid user!";
    }
}
