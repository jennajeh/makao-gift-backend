package kr.megaptera.makaobank.dtos;

import java.util.Objects;

public class UserCreateDto {
    private Long id;
    private String username;
    private String name;

    public UserCreateDto() {
    }

    public UserCreateDto(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserCreateDto(" +
                "id:" + id + ", " +
                "username:" + username + ", " +
                "name:" + name + ")";
    }

    @Override
    public boolean equals(Object other) {
        UserCreateDto otherUserCreateDto = (UserCreateDto) other;

        return Objects.equals(id, otherUserCreateDto.id) &&
                Objects.equals(username, otherUserCreateDto.username) &&
                Objects.equals(name, otherUserCreateDto.name);
    }

}
