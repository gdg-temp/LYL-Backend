package GDG.backend.global.utils.user;

import GDG.backend.domain.user.domain.User;
import GDG.backend.domain.user.domain.repository.UserRepository;
import GDG.backend.global.exception.UserNotFoundException;
import GDG.backend.global.utils.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUtilsImpl implements UserUtils{

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public User getUserFromSecurityContext() {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        User user = getUserById(currentUserId);
        return user;
    }

    @Override
    public User getUserEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> UserNotFoundException.EXCEPTION);}
}
