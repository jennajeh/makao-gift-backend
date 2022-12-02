package kr.megaptera.makaobank.exceptions;

public class ProductNotFount extends RuntimeException {
    public ProductNotFount(Long id) {
        super("Product not found - " + id);
    }
}
