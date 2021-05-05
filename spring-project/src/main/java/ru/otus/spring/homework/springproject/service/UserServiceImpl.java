package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.mappers.UserMapper;
import ru.otus.spring.homework.springproject.models.dto.UserDto;
import ru.otus.spring.homework.springproject.models.entity.Role;
import ru.otus.spring.homework.springproject.models.entity.User;
import ru.otus.spring.homework.springproject.repositories.RoleRepository;
import ru.otus.spring.homework.springproject.repositories.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(
        UserRepository userRepository,
        RoleRepository roleRepository,
        PasswordEncoder passwordEncoder,
        UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        log.debug("getAllUsers");
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDto getUser(Long userId) {
        log.debug("getUser");
        return userMapper.toDto(userRepository.getOne(userId));
    }

    @Override
    public List<UserDto> getUsers(List<String> userIds) {
        log.debug("getUsers");
        var ids = userIds.stream()
            .map(Long::parseLong)
            .collect(Collectors.toList());
        return userMapper.toDtoList(userRepository.findAllById(ids));
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        log.debug("addUser");
        var entity = userMapper.toEntity(userDto, roleRepository);
        return userMapper.toDto(userRepository.save(entity));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        log.debug("updateUser");
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto, roleRepository)));
    }

    @Override
    public void deleteUser(Long userId) {
        log.debug("deleteUser");
        userRepository.deleteById(userId);
    }

    @Override
    public long getCount() {
        log.debug("getCount");
        return userRepository.count();
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        Role userRole = roleRepository.findByRoleName("USER").orElse(null);
        user.setRoles(List.of(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("user")
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsernameAndPassword(String username, String password) {
        User user = findByUsername(username);
        if (Objects.nonNull(user)) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<String> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            var user = userRepository.findByUsername(currentUserName).orElse(null);
            if (Objects.nonNull(user)) {
                return Optional.of(user.getUsername());
            }
        }
        return null;
    }

}
