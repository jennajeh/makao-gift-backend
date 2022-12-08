package kr.megaptera.makaobank.exceptions;

public class InvalidUser extends RuntimeException {
    public InvalidUser() {
        super("Invalid user");
    }
}
