package kr.megaptera.makaobank.dtos;

import java.time.LocalDateTime;

public class OrderDto {
    private Long id;
    private Integer quantity;
    private Long totalPrice;
    private String receiver;
    private String address;
    private String message;
    private ProductDto product;
    private LocalDateTime createdAt;

    public OrderDto() {
    }

    public OrderDto(Long id, Integer quantity, Long totalPrice, String receiver,
                    String address, String message, ProductDto product, LocalDateTime createdAt) {
        this.id = id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
        this.product = product;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }

    public ProductDto getProduct() {
        return product;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
