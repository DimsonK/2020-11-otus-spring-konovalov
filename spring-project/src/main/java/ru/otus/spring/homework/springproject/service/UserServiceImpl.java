package ru.otus.spring.homework.springproject.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.models.entity.Role;
import ru.otus.spring.homework.springproject.models.entity.User;
import ru.otus.spring.homework.springproject.repositories.RoleRepository;
import ru.otus.spring.homework.springproject.repositories.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User saveUser(User user) {
        Role userRole = roleRepository.findByRoleName("USER").orElse(null);
        user.setRoles(List.of(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Transactional(readOnly = true)
    public User findByUsernameAndPassword(String username, String password) {
        User user = findByUsername(username);
        if(Objects.nonNull(user)) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }


}
