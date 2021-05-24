package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.spring.homework.springproject.models.dto.IssueDto;
import ru.otus.spring.homework.springproject.models.entity.Issue;
import ru.otus.spring.homework.springproject.repositories.OrderRepository;
import ru.otus.spring.homework.springproject.repositories.UserRepository;

import java.util.List;

@Mapper(uses = {UserMapper.class, OrderMapper.class, InstanceMapper.class, IssueInstanceMapper.class})
public interface IssueMapper {

    @Mapping(source = "user", target = "userId")
    @Mapping(source = "order", target = "orderId")
    IssueDto toDto(Issue issue);

    List<IssueDto> toDtoList(List<Issue> issues);

    @Mapping(source = "orderId", target = "order")
    @Mapping(source = "userId", target = "user")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "instances", ignore = true)
    Issue toEntity(IssueDto issueDto, @Context UserRepository userRepository, @Context OrderRepository orderRepository);

    List<Issue> toEntityList(List<IssueDto> issueDto);
}
