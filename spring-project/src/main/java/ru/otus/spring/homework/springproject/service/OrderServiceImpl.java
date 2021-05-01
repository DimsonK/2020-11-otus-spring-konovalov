package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.mappers.OrderMapper;
import ru.otus.spring.homework.springproject.models.dto.OrderDto;
import ru.otus.spring.homework.springproject.repositories.OrderRepository;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getAll() {
        log.debug("getAll()");
        return orderMapper.toDtoList(orderRepository.findAll());
    }

    @Override
    public OrderDto getOrder(Long OrderId) {
        return null;
    }

    @Override
    public List<OrderDto> getOrders(List<String> OrderIds) {
        return null;
    }

    @Override
    public OrderDto addOrder(String OrderName) {
        return null;
    }

    @Override
    public OrderDto updateOrder(OrderDto OrderDto) {
        return null;
    }

    @Override
    public void deleteOrder(Long OrderId) {

    }

    @Override
    public long getCount() {
        return 0;
    }
}
