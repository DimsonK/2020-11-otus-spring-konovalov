package ru.otus.spring.homework.springproject.security;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework.springproject.mappers.UserMapper;
import ru.otus.spring.homework.springproject.models.dto.UserDto;
import ru.otus.spring.homework.springproject.models.entity.Role;
import ru.otus.spring.homework.springproject.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class AuthProvider implements AuthenticationProvider {

    private static final String AUTH_SERVICE = "authService";

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AuthProvider(
            UserServiceImpl userService, @Lazy PasswordEncoder passwordEncoder,
            UserMapper userMapper
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    @CircuitBreaker(name = AUTH_SERVICE, fallbackMethod = "fallback")
    @Bulkhead(name = AUTH_SERVICE, fallbackMethod = "bulkHeadGetAuthentication", type = Type.SEMAPHORE)
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        var user = userService.findByUsername(username);

        if (Objects.nonNull(user) && passwordEncoder.matches(password, user.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Role privilege : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + privilege.getRoleName()));
            }
            return new UsernamePasswordAuthenticationToken
                    (userMapper.toDto(user), password, authorities);
        } else {
            throw new BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public Authentication bulkHeadGetAuthentication(Exception t) throws AuthenticationException {
        log.info("bulkHeadGetAuthentication");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
        return new UsernamePasswordAuthenticationToken
                (new UserDto("anonymous", "", "", 20, 2, List.of("ANONYMOUS")),
                        "password", authorities);
    }

    public Authentication fallback(Authentication authentication, Exception t) throws AuthenticationException {
        throw new RuntimeException("что то с сервисом");
    }

}
