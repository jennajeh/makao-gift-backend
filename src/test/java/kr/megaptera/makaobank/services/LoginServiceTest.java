package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.exceptions.LoginFailed;
import kr.megaptera.makaobank.models.User;
import kr.megaptera.makaobank.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
    private LoginService loginService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);
        loginService = new LoginService(userRepository, passwordEncoder);
    }

    @Test
    void loginSuccess() {
        String username = "test1";
        String password = "Asdf1234!";

        User user = User.fake();
        user.changePassword(password, passwordEncoder);

        given(userRepository.findByUsername(username))
                .willReturn((Optional.of(user)));

        assertThat(loginService.login(username, password).username())
                .isEqualTo(username);
    }

    @Test
    void loginWithWrongUsername() {
        String username = "Jenna1234!";
        String password = "Asdf1234!";

        assertThrows(LoginFailed.class, () -> loginService.login(username, password));
    }

    @Test
    void loginWithWrongPassword() {
        String username = "test1!";
        String password = "Asdf1234!";

        User user = User.fake();
        user.changePassword("xxx", passwordEncoder);

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(user));

        assertThrows(LoginFailed.class,
                () -> loginService.login(username, password));
    }
}
