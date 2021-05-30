package ru.otus.spring.homework.springproject.models.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IssueInstanceId implements Serializable {

    @Column(name = "ISSUE_ID")
    private Long issueId;

    @Column(name = "INSTANCE_ID")
    private Long instanceId;

    public IssueInstanceId() {
    }

    public IssueInstanceId(
        Long issueId, Long instanceId
    ) {
        this.issueId = issueId;
        this.instanceId = instanceId;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IssueInstanceId that = (IssueInstanceId) o;
        return Objects.equals(issueId, that.issueId) &&
            Objects.equals(instanceId, that.instanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueId, instanceId);
    }
}
