package com.example.vehicles.api.v1.service;

import com.example.vehicles.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TokenService {

    public static String ROLE_USER = "ROLE_USER";
    public static String ROLE_ADMIN = "ROLE_ADMIN";
    public static String ROLES_PROPERTY = "user_roles";

    @Value("${TOKEN_SECRET:String Aleatoria Secret}")
    private String key = "String Aleatoria Secret";

    @Value("${TOKEN_MINUTES_EXPIRATION:30}")
    private int minutesExpiration = 30;

    private long expirationTime = minutesExpiration * 60 * 1000;

    public String generateTokenUser(User user) {
        Set<String> userRoles = new HashSet<>();
        userRoles.add(ROLE_USER);
        return generateToken(user, userRoles);
    }

    /**
     * Futuramente quando for necessário autenticar admin também seria necessário criar um relation user_roles
     * e criar o token com esse método
     */
    public String generateTokenAdmin(User user) {
        Set<String> adminRoles = new HashSet<>();
        adminRoles.add(ROLE_USER);
        adminRoles.add(ROLE_ADMIN);
        return generateToken(user, adminRoles);
    }

    public String generateToken(User user, Set<String> userRoles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user.getId());
        claims.put("user_name", user.getName());
        claims.put("user_email", user.getEmail());
        claims.put(ROLES_PROPERTY, userRoles);
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(user.getEmail())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Claims decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}