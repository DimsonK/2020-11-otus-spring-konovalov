package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.*;
import ru.otus.spring.homework.springproject.models.dto.OrderDto;
import ru.otus.spring.homework.springproject.models.entity.Book;
import ru.otus.spring.homework.springproject.models.entity.Order;
import ru.otus.spring.homework.springproject.repositories.BookRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper
public interface OrderMapper {

    @Mapping(source = "books", target = "bookIds")
    OrderDto toDto(Order order);

    default String mapBookToString(Book book) {
        return Objects.isNull(book) ? null : book.getId().toString();
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "books", ignore = true)
    Order toEntity(OrderDto orderDto, @Context BookRepository bookRepository);

    @AfterMapping
    default void fillBooks(OrderDto source, @MappingTarget Order target, @Context BookRepository bookRepository) {
        var books = source.getBookIds().stream()
            .map(id -> bookRepository.findById(Long.parseLong(id)).orElse(null))
            .collect(Collectors.toList());
        target.setBooks(books);
    }

    List<OrderDto> toDtoList(List<Order> orders);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    List<Order> toEntityList(List<OrderDto> orderDto);

}
