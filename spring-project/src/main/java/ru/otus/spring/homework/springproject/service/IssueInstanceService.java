package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.dto.IssueInstanceDto;
import ru.otus.spring.homework.springproject.models.dto.IssueInstanceStatusDto;
import ru.otus.spring.homework.springproject.models.entity.IssueInstanceId;
import ru.otus.spring.homework.springproject.models.enums.IssueStatus;

import java.util.List;

public interface IssueInstanceService {

    List<IssueInstanceDto> getAll();

    List<IssueInstanceDto> getInstancesByIssueId(Long issueId);

    List<IssueInstanceDto> getInstancesByUserId(Long userId);

    void setStatus(IssueInstanceStatusDto data);

    long getCount();
}
