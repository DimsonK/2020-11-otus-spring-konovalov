package ru.otus.spring.homework.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.springproject.models.entity.IssueInstance;
import ru.otus.spring.homework.springproject.models.entity.IssueInstanceId;

@Repository
public interface IssueInstanceRepository extends JpaRepository<IssueInstance, IssueInstanceId> {
}
