package ru.otus.spring.homework.springproject.models.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import ru.otus.spring.homework.springproject.service.UserServiceImpl;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Optional<String> getCurrentAuditor() {
        return userService.getCurrentUser();
    }
}
