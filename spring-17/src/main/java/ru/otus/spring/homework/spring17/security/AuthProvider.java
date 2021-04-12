package ru.otus.spring.homework.spring17.security;

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
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring17.mappers.UserMapper;
import ru.otus.spring.homework.spring17.models.entity.Role;
import ru.otus.spring.homework.spring17.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AuthProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AuthProvider(
            UserRepository userRepository,
            @Lazy PasswordEncoder passwordEncoder,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        var user = userRepository.findByUsername(username).orElse(null);

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
}
