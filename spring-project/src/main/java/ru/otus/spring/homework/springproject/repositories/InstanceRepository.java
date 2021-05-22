package ru.otus.spring.homework.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.springproject.models.entity.Book;
import ru.otus.spring.homework.springproject.models.entity.Instance;

import java.util.List;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Long> {
    List<Instance> getInstanceByBook(Book book);
}
