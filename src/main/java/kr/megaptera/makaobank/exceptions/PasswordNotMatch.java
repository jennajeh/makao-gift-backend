package kr.megaptera.makaobank.exceptions;

public class PasswordNotMatch extends RuntimeException {
    public PasswordNotMatch() {
        super("Password doesn't match");
    }
}
