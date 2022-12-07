package kr.megaptera.makaobank.exceptions;

public class UsernameAlreadyTaken extends RuntimeException {
    public UsernameAlreadyTaken(String username) {
        super(username + " already taken");
    }
}
