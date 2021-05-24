package ru.otus.spring.homework.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.springproject.models.entity.Book;
import ru.otus.spring.homework.springproject.models.entity.Instance;

import java.util.List;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Long> {
    List<Instance> getInstanceByBook(Book book);

    @Query(
        "select i from Instance i " +
            "inner join i.book b " +
            "left join i.issues ii " +
            "where " +
            "(ii.id is null or (ii.createdAt = (select max(ii2.createdAt) from IssueInstance ii2 where ii2.instance = i and ii.status = 'RETURNED'))) and " +
            "b in (:books)")
    List<Instance> getInstancesForIssueByBookIds(@Param("books") List<Book> books);
}
