package kr.megaptera.makaobank.dtos;

public class UserRegisterDto {
    private String username;
    private String name;
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
