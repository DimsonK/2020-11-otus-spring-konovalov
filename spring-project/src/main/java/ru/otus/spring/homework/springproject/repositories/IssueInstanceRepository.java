package ru.otus.spring.homework.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.springproject.models.entity.Issue;
import ru.otus.spring.homework.springproject.models.entity.IssueInstance;
import ru.otus.spring.homework.springproject.models.entity.IssueInstanceId;

import java.util.List;

@Repository
public interface IssueInstanceRepository extends JpaRepository<IssueInstance, IssueInstanceId> {
    List<IssueInstance> findIssueInstanceByIssue(Issue issue);
}
