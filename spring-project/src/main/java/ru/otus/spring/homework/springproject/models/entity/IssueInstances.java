package ru.otus.spring.homework.springproject.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.otus.spring.homework.springproject.models.enums.IssueStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ISSUE_INSTANCES")
@IdClass(IssueInstancesId.class)
public class IssueInstances extends AuditModel {
    @Id
    @ManyToOne
    @JoinColumn(name = "ISSUE_ID", referencedColumnName = "ID")
    private Issue issue;

    @Id
    @ManyToOne
    @JoinColumn(name = "INSTANCE_ID", referencedColumnName = "ID")
    private Instance instance;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    @Column(name = "RETURN_TIME", nullable = false)
    private LocalDateTime returnTime;

}
