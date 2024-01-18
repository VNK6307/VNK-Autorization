package ru.laguna.vnkauthorization.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laguna.vnkauthorization.exception.InvalidCredentials;
import ru.laguna.vnkauthorization.exception.UnauthorizedUser;
import ru.laguna.vnkauthorization.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorizationService {
    private final UserRepository userRepository;

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {// ToDo Что означает <?>?
        return str == null || str.isEmpty();
    }


}
