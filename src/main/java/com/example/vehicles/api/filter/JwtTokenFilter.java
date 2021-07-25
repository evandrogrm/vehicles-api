package com.example.vehicles.api.filter;

import com.example.vehicles.api.v1.service.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.vehicles.api.v1.service.TokenService.*;

public class JwtTokenFilter extends GenericFilterBean {

    private TokenService tokenService;

    public JwtTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String jwtToken = httpServletRequest.getHeader("Authorization");

            if (!StringUtils.isEmpty(jwtToken)) {
                String authorizationSchema = "Bearer";
                if (!jwtToken.contains(authorizationSchema)) {
                    throw new InsufficientAuthenticationException("Authorization schema not found");
                }
                jwtToken = jwtToken.substring(authorizationSchema.length()).trim();

                Claims claims = tokenService.decodeToken(jwtToken);
                String email = claims.getSubject();

                List<String> roles = (List<String>) claims.get(ROLES_PROPERTY);
                if (roles != null && !roles.isEmpty()) {
                    List<GrantedAuthority> userGranted = new ArrayList<>();
                    if (roles.contains(ROLE_ADMIN)) {
                        userGranted.add(new SimpleGrantedAuthority(ROLE_USER));
                        userGranted.add(new SimpleGrantedAuthority(ROLE_ADMIN));
                    } else {
                        userGranted.add(new SimpleGrantedAuthority(ROLE_USER));
                    }

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            email, null, userGranted);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
