package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.spring.homework.springproject.models.dto.IssueDto;
import ru.otus.spring.homework.springproject.models.entity.Issue;

import java.util.List;

@Mapper(uses = {UserMapper.class, OrderMapper.class, InstanceMapper.class})
public interface IssueMapper {

    @Mapping(source = "user", target = "userId")
    @Mapping(source = "order", target = "orderId")
    IssueDto toDto(Issue issue);

    List<IssueDto> toDtoList(List<Issue> issues);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    Issue toEntity(IssueDto issueDto);

    List<Issue> toEntityList(List<IssueDto> issueDto);
}
