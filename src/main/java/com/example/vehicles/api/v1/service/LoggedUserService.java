package com.example.vehicles.api.v1.service;

import com.example.vehicles.api.v1.repository.UserRepositoryV1;
import com.example.vehicles.api.v1.service.exception.AbstractException;
import com.example.vehicles.api.v1.service.exception.UserNotFoundException;
import com.example.vehicles.config.exception.ForbiddenException;
import com.example.vehicles.domain.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.example.vehicles.api.v1.service.TokenService.USER_ID_PROPERTY;

@Service
public class LoggedUserService {

    private static UserRepositoryV1 userRepositoryV1;

    @Autowired
    public LoggedUserService(UserRepositoryV1 userRepositoryV1) {
        LoggedUserService.userRepositoryV1 = userRepositoryV1;
    }

    public static User getLoggedUser() throws AbstractException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new ForbiddenException("Incorrect token exception");
        }
        Claims claims = (Claims) authentication.getCredentials();
        String userId = claims.get(USER_ID_PROPERTY, String.class);
        return userRepositoryV1.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
