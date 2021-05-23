package ru.otus.spring.homework.springproject.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.homework.springproject.models.enums.IssueStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueInstanceStatusDto {
    private String issueId;
    private String instanceId;
    private IssueStatus status;
}
