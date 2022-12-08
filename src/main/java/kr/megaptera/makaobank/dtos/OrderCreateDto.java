package kr.megaptera.makaobank.dtos;

import java.util.Objects;

public class OrderCreateDto {
    private Long id;
    private Long productId;
    private Integer quantity;

    public OrderCreateDto() {
    }

    public OrderCreateDto(Long id, Long productId, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderCreateDto(" +
                "id:" + id + ", " +
                "productId:" + productId + ", " +
                "quantity:" + quantity + ")";
    }

    @Override
    public boolean equals(Object other) {
        OrderCreateDto otherOrderCreateDto = (OrderCreateDto) other;

        return Objects.equals(id, otherOrderCreateDto.id) &&
                Objects.equals(productId, otherOrderCreateDto.productId) &&
                Objects.equals(quantity, otherOrderCreateDto.quantity);
    }
}
