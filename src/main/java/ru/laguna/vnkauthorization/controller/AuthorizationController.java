package ru.laguna.vnkauthorization.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.laguna.vnkauthorization.exception.InvalidCredentials;
import ru.laguna.vnkauthorization.exception.UnauthorizedUser;
import ru.laguna.vnkauthorization.exception.UserErrorResponse;
import ru.laguna.vnkauthorization.service.Authorities;
import ru.laguna.vnkauthorization.service.AuthorizationService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthorizationController {
    private final AuthorizationService service;

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleUnauthorizedUserException(UnauthorizedUser exception) {
        UserErrorResponse response = new UserErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleInvalidCredentialsException(InvalidCredentials exception) {
        UserErrorResponse response = new UserErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
