package kr.megaptera.makaobank.exceptions;

public class OrderFailed extends RuntimeException {
    public OrderFailed() {
        super("Order failed");
    }
}
