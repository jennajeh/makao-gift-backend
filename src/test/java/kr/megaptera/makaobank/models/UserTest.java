package kr.megaptera.makaobank.models;

import kr.megaptera.makaobank.dtos.UserCreateDto;
import kr.megaptera.makaobank.dtos.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;


class UserTest {

    @Test
    void creation() {
        User user = new User(1L, "Test1", "전제나", "Test123!", 50_000L);

        assertThat(user.id()).isEqualTo(1L);
        assertThat(user.name()).isEqualTo("전제나");
    }

    @Test
    void setInitialAmount() {
        Long amount = 5_000_000L;

        User user = new User();

        user.setInitialAmount();

        assertThat(user.amount()).isEqualTo(amount);
    }

    @Test
    void authenticate() {
        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);

        User user = new User();

        user.changePassword("Test123!", passwordEncoder);

        assertThat(user.authenticate("Test123!", passwordEncoder)).isTrue();
        assertThat(user.authenticate("xxx", passwordEncoder)).isFalse();
    }

    @Test
    void toUserDto() {
        User user = new User(1L, "Test1", "전제나", "Test123!", 50_000L);

        UserDto userDto = user.toUserDto();

        assertThat(userDto).isEqualTo(new UserDto(1L, "Test1", 50_000L));
    }

    @Test
    void toCreateDto() {
        User user = new User(1L, "Test1", "전제나", "Test123!", 50_000L);

        UserCreateDto userCreateDto = user.toCreateDto();

        assertThat(userCreateDto).isEqualTo(new UserCreateDto(1L, "Test1", "전제나"));
    }

    @Test
    void order() {
        User user = new User();

        user.setInitialAmount();

        Long initialAmount = user.amount();

        Product product = Product.fake();

        Integer quantity = 2;

        user.order(product, quantity);

        assertThat(user.amount()).isEqualTo(initialAmount - product.price() * quantity);
    }
}
