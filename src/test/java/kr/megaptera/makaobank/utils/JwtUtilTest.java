package kr.megaptera.makaobank.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    @Test
    void encodeAndDecode() {
        Long userId = 1004L;

        JwtUtil jwtUtil = new JwtUtil("SECRET");

        String token = jwtUtil.encode(userId);

        assertThat(jwtUtil.decode(token)).isEqualTo(userId);
    }
}
