package kr.megaptera.makaobank.models;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;


class UserTest {

    @Test
    void creation() {
        User user = new User(1L, "Test1", "전제나", "Test123!", 50_000L);

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("전제나");
    }

    @Test
    void setInitialAmount() {
        Long amount = 50_000L;

        User user = new User();

        user.setInitialAmount();

        assertThat(user.getAmount()).isEqualTo(amount);
    }

    @Test
    void authenticate() {
        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);

        User user = new User();

        user.changePassword("Test123!", passwordEncoder);

        assertThat(user.authenticate("Test123!", passwordEncoder)).isTrue();
        assertThat(user.authenticate("xxx", passwordEncoder)).isFalse();
    }
}
