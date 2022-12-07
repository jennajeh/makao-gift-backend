package kr.megaptera.makaobank.controllers;

import jakarta.validation.Valid;
import kr.megaptera.makaobank.dtos.UserCreateDto;
import kr.megaptera.makaobank.dtos.UserDto;
import kr.megaptera.makaobank.dtos.UserRegisterDto;
import kr.megaptera.makaobank.exceptions.PasswordNotMatch;
import kr.megaptera.makaobank.exceptions.UserNotFound;
import kr.megaptera.makaobank.exceptions.UsernameAlreadyTaken;
import kr.megaptera.makaobank.models.User;
import kr.megaptera.makaobank.services.CreateUserService;
import kr.megaptera.makaobank.services.GetUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private GetUserService getUserService;
    private CreateUserService createUserService;

    public UserController(GetUserService getUserService, CreateUserService createUserService) {
        this.getUserService = getUserService;
        this.createUserService = createUserService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreateDto signup(
            @Valid @RequestBody UserRegisterDto userRegisterDto
    ) {
        User user = createUserService.create(userRegisterDto);

        return user.toCreateDto();
    }

    @GetMapping("/me")
    public UserDto user(@RequestAttribute Long userId) {
        User user = getUserService.detail(userId);

        return user.toUserDto();
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFound() {
        return "User not found";
    }

    @ExceptionHandler(UsernameAlreadyTaken.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String usernameAlreadyTaken() {
        return "Username already taken";
    }

    @ExceptionHandler(PasswordNotMatch.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String passwordNotMatch() {
        return "Password doesn't match";
    }
}
