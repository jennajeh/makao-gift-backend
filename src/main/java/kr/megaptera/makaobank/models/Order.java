package kr.megaptera.makaobank.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.megaptera.makaobank.dtos.OrderCreateDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Long totalPrice;
    private String receiver;
    private String address;
    private String message;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order() {
    }

    public Order(Long productId, Integer quantity,
                 String receiver, String address, String message) {
        this.productId = productId;
        this.quantity = quantity;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
    }

    public Order(Long id, Long userId, Long productId,
                 Integer quantity, Long totalPrice, String receiver,
                 String address, String message) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
    }

    public static Order fake() {
        return new Order(1L, 1L, 1L, 1, 10_000L, "강보니", "서울시 성동구 성수동", "생일 축하해!");
    }

    public Long id() {
        return id;
    }

    public Long userId() {
        return userId;
    }

    public Long productId() {
        return productId;
    }

    public Integer quantity() {
        return quantity;
    }

    public Long totalPrice() {
        return totalPrice;
    }

    public Long totalPrice(Long price) {
        return price * quantity;
    }

    public String receiver() {
        return receiver;
    }

    public String address() {
        return address;
    }

    public String message() {
        return message;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public OrderCreateDto toCreateDto() {
        return new OrderCreateDto(id, productId, quantity);
    }
}
