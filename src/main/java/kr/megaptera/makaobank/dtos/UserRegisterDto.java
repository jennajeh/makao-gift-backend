package kr.megaptera.makaobank.dtos;

import jakarta.validation.constraints.Pattern;

public class UserRegisterDto {
    @Pattern(regexp = "^[a-z|0-9]{4,16}$")
    private String username;

    @Pattern(regexp = "^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{3,7}$")
    private String name;

    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}")
    private String password;
    private String passwordCheck;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String username, String name, String password, String passwordCheck) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.passwordCheck = passwordCheck;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }
}
