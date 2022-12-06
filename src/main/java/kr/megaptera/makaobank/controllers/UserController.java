package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.UserDto;
import kr.megaptera.makaobank.exceptions.UserNotFound;
import kr.megaptera.makaobank.models.User;
import kr.megaptera.makaobank.services.GetUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private GetUserService getUserService;

    public UserController(GetUserService getUserService) {
        this.getUserService = getUserService;
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
}
