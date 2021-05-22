package ru.otus.spring.homework.springproject.models.entity;

import lombok.*;
import ru.otus.spring.homework.springproject.models.enums.IssueStatus;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "INSTANCE_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "INSTANCES")
public class Instance extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSTANCE_SEQUENCE")
    private Long id;

    @Column(name = "INVENTORY_NUMBER", nullable = false, unique = true)
    private String inventoryNumber;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @OneToMany(mappedBy = "instance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IssueInstance> issues;

    public void addIssue(Issue issue) {
        IssueInstance issueInstance = new IssueInstance(issue, this, IssueStatus.ISSUED, null);
        issues.add(issueInstance);
        issue.getInstances().add(issueInstance);
    }

}
