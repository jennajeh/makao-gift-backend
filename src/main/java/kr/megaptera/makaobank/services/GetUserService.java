package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.exceptions.UserNotFound;
import kr.megaptera.makaobank.models.User;
import kr.megaptera.makaobank.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetUserService {
    private UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User detail(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound(id));
        
        return user;
    }
}
