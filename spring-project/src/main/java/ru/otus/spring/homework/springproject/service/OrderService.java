package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAll();

    OrderDto getOrder(Long orderId);

    List<OrderDto> getOrders(List<String> orderIds);

    OrderDto addOrder(OrderDto orderDto);

    OrderDto updateOrder(OrderDto orderDto);

    void deleteOrder(Long orderId);

    long getCount();
}
