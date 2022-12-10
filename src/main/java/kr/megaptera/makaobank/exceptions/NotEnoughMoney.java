package kr.megaptera.makaobank.exceptions;

public class NotEnoughMoney extends RuntimeException {
    public NotEnoughMoney() {
        super("Not enough money");
    }
}
