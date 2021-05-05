package ru.otus.spring.homework.springproject.models.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "ISSUE_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "ISSUE")
public class Issue extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ISSUE_SEQUENCE")
    private Long id;

    @Column(name = "ISSUE_TIME", nullable = false)
    private LocalDateTime issueTime;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(targetEntity = Order.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @OneToMany(mappedBy = "issue", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<IssueInstance> instances;

}
