package ru.otus.spring.homework.springproject.models.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class IssueInstanceId implements Serializable {
    private Long issue;
    private Long instance;
}
