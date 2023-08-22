package GDG.backend.global.utils.user;

import GDG.backend.domain.user.domain.User;

public interface UserUtils {
    User getUserById(Long id);

    User getUserFromSecurityContext();

    User getUserEmail(String email);
}
