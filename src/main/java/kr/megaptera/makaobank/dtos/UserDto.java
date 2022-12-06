package kr.megaptera.makaobank.dtos;

import java.util.Objects;

public class UserDto {
    private Long id;
    private String username;
    private Long amount;

    public UserDto() {
    }

    public UserDto(Long id, String username, Long amount) {
        this.id = id;
        this.username = username;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "UserDto(" +
                "id:" + id + ", " +
                "username:" + username + ", " +
                "amount:" + amount + ")";
    }

    @Override
    public boolean equals(Object other) {
        UserDto otherUserDto = (UserDto) other;

        return Objects.equals(id, otherUserDto.id) &&
                Objects.equals(username, otherUserDto.username) &&
                Objects.equals(amount, otherUserDto.amount);
    }
}
