package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.UserRegisterDto;
import kr.megaptera.makaobank.exceptions.UsernameAlreadyTaken;
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
import static org.mockito.Mockito.verify;

class CreateUserServiceTest {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CreateUserService createUserService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);
        createUserService = new CreateUserService(userRepository, passwordEncoder);
    }

    @Test
    void create() {
        UserRegisterDto userRegisterDto = new UserRegisterDto(
                "Test1!", "전제나", "Test123!", "Test123!");

        Long initialAmount = 5_000_000L;

        User user = createUserService.create(userRegisterDto);

        assertThat(user).isNotNull();
        assertThat(user.amount()).isEqualTo(initialAmount);

        verify(userRepository).save(user);
    }

    @Test
    void createWithExistedUsername() {
        UserRegisterDto userRegisterDto = new UserRegisterDto(
                "Jenna1234!", "전제나", "Asdf1234!", "Asdf1234!");

        given(userRepository.findByUsername(userRegisterDto.getUsername()))
                .willReturn(Optional.of(User.fake()));

        assertThrows(UsernameAlreadyTaken.class,
                () -> createUserService.create(userRegisterDto));
    }
}
