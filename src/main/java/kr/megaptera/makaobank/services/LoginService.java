package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.exceptions.LoginFailed;
import kr.megaptera.makaobank.models.User;
import kr.megaptera.makaobank.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new LoginFailed());

        if (!user.authenticate(password, passwordEncoder)) {
            throw new LoginFailed();
        }

        return user;
    }
}
