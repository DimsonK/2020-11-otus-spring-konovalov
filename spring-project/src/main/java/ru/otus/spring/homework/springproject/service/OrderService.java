package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAll();

    OrderDto getOrder(Long OrderId);

    List<OrderDto> getOrders(List<String> OrderIds);

    OrderDto addOrder(String OrderName);

    OrderDto updateOrder(OrderDto OrderDto);

    void deleteOrder(Long OrderId);

    long getCount();
}
