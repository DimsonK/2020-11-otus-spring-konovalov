package ru.otus.spring.homework.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.springproject.models.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);
    Optional<User> findByUsernameAndPassword(String userName, String password);
}
