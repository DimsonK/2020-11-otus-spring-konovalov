package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.otus.spring.homework.springproject.models.dto.OrderDto;
import ru.otus.spring.homework.springproject.models.entity.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto toDto(Order order);

    List<OrderDto> toDtoList(List<Order> orders);

    Order toEntity(OrderDto orderDto);

    List<Order> toEntityList(List<OrderDto> orderDto);
}
