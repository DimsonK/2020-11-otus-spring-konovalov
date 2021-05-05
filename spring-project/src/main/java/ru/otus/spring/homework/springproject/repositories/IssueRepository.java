package ru.otus.spring.homework.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.springproject.models.entity.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
}
