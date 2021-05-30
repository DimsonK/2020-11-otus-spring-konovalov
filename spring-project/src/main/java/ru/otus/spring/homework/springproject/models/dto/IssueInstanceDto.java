package ru.otus.spring.homework.springproject.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.homework.springproject.models.enums.IssueStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueInstanceDto {
    private IssueDto issue;
    private InstanceDto instance;
    private IssueStatus status;
    private LocalDateTime returnTime;
}
