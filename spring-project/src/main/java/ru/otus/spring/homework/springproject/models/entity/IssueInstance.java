package ru.otus.spring.homework.springproject.models.entity;

import ru.otus.spring.homework.springproject.models.enums.IssueStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "IssueInstance")
@Table(name = "ISSUE_INSTANCES")
public class IssueInstance extends AuditModel {

    @EmbeddedId
    private IssueInstanceId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("issueId")
    private Issue issue;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("instanceId")
    private Instance instance;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    @Column(name = "RETURN_TIME", nullable = false)
    private LocalDateTime returnTime;

    public IssueInstance() {
    }

    public IssueInstance(
        Issue issue,
        Instance instance,
        IssueStatus status,
        LocalDateTime returnTime
    ) {
        this.issue = issue;
        this.instance = instance;
        this.status = status;
        this.returnTime = returnTime;
        this.id = new IssueInstanceId(issue.getId(), instance.getId());
    }

    public IssueInstanceId getId() {
        return id;
    }

    public void setId(IssueInstanceId id) {
        this.id = id;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IssueInstance that = (IssueInstance) o;
        return Objects.equals(issue, that.issue) &&
            Objects.equals(instance, that.instance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issue, instance);
    }
}
