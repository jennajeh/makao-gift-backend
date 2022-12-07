package kr.megaptera.makaobank.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(Long id) {
        super("User not found - " + id);
    }
}
