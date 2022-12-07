package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.UserRegisterDto;
import kr.megaptera.makaobank.exceptions.PasswordNotMatch;
import kr.megaptera.makaobank.exceptions.UsernameAlreadyTaken;
import kr.megaptera.makaobank.models.User;
import kr.megaptera.makaobank.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateUserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepository userRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(UserRegisterDto userRegisterDto) {
        String name = userRegisterDto.getName();
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String passwordCheck = userRegisterDto.getPasswordCheck();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyTaken(username);
        }

        if (!password.equals(passwordCheck)) {
            throw new PasswordNotMatch();
        }

        User user = new User(name, username);

        user.setInitialAmount();

        user.changePassword(password, passwordEncoder);

        userRepository.save(user);

        return user;
    }
}
