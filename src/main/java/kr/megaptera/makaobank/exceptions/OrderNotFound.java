package kr.megaptera.makaobank.exceptions;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(Long id) {
        super("Order not found: " + id);
    }
}
