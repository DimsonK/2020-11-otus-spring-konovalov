package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.spring.homework.springproject.models.dto.IssueInstanceDto;
import ru.otus.spring.homework.springproject.models.entity.IssueInstance;

import java.util.List;

@Mapper(uses = {IssueMapper.class, InstanceMapper.class})
public interface IssueInstanceMapper {

    IssueInstanceDto toDto(IssueInstance issueInstance);

    List<IssueInstanceDto> toDtoList(List<IssueInstance> issueInstances);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    IssueInstance toEntity(IssueInstanceDto issueInstanceDto);

    List<IssueInstance> toEntityList(List<IssueInstanceDto> issueInstanceDto);
}
