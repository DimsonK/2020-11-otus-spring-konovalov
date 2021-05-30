package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.spring.homework.springproject.models.dto.InstanceDto;
import ru.otus.spring.homework.springproject.models.entity.Instance;
import ru.otus.spring.homework.springproject.repositories.BookRepository;

import java.util.List;

@Mapper(uses = {BookMapper.class})
public interface InstanceMapper {

    @Mapping(source = "book", target = "bookId")
    InstanceDto toDto(Instance instance);

    List<InstanceDto> toDtoList(List<Instance> instances);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(source = "bookId", target = "book")
    Instance toEntity(InstanceDto instanceDto, @Context BookRepository bookRepository);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(source = "bookId", target = "book")
    List<Instance> toEntityList(List<InstanceDto> instanceDto);
}
