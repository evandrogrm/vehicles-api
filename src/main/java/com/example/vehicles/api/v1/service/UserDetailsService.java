package com.example.vehicles.api.v1.service;

import com.example.vehicles.api.v1.repository.UserRepositoryV1;
import com.example.vehicles.api.v1.service.exception.AbstractException;
import com.example.vehicles.api.v1.service.exception.UserEmailNotFoundException;
import com.example.vehicles.api.v1.service.exception.UserNotFoundException;
import com.example.vehicles.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.vehicles.api.v1.service.TokenService.ROLE_USER;

@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String email) {
        try {
            String lowercaseEmail = email.toLowerCase();
            User user = userService.findByEmail(lowercaseEmail)
                    .orElseThrow(() -> new UserEmailNotFoundException(email));
//        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
//                .map(role -> role.getGrantedAuthority()).collect(Collectors.toList());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_USER));

            return new org.springframework.security.core.userdetails.User(lowercaseEmail, null,
                    grantedAuthorities);
        } catch (UserEmailNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
