package com.company.userandadminauthorities.security;

import com.company.userandadminauthorities.dto.UserDto;
import com.company.userandadminauthorities.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authentication = request.getHeader("Authorization");//Basic amF2YTpyb290
        if (!StringUtils.isBlank(authentication) && authentication.startsWith("Basic")) {
            String usernameAndPasswordBase64 = authentication.substring(6);
            String usernameAndPassword = new String(Base64.getDecoder().decode(usernameAndPasswordBase64));//java:root
            String username = usernameAndPassword.split(":")[0];//java
            String password = usernameAndPassword.split(":")[1];//root
            UserDto userDto = this.userService.loadUserByUsername(username);
            if (passwordEncoder.matches(password, userDto.getPassword())) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDto,
                                password,
                                userDto.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
