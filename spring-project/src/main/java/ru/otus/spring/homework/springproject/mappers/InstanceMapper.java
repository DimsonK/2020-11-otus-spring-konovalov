package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.spring.homework.springproject.models.dto.InstanceDto;
import ru.otus.spring.homework.springproject.models.entity.Instance;

import java.util.List;

@Mapper
public interface InstanceMapper {

    InstanceDto toDto(Instance instance);

    List<InstanceDto> toDtoList(List<Instance> instances);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    Instance toEntity(InstanceDto instanceDto);

    List<Instance> toEntityList(List<InstanceDto> instanceDto);
}
