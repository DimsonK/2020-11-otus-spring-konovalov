package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.spring.homework.springproject.models.dto.BasketDto;
import ru.otus.spring.homework.springproject.models.entity.Basket;

import java.util.List;

@Mapper(uses = {
    AuthorMapper.class, RoleMapper.class, GenreMapper.class,
    BookMapper.class
})
public interface BasketMapper {

    @Mapping(target = "books.comments", ignore = true)
    BasketDto toDto(Basket basket);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    Basket toEntity(BasketDto basketDto);

    List<BasketDto> toDtoList(List<Basket> basketList);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    List<Basket> toEntityList(List<BasketDto> basketDtoList);

}
