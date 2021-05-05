package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.mappers.OrderMapper;
import ru.otus.spring.homework.springproject.models.dto.OrderDto;
import ru.otus.spring.homework.springproject.repositories.BookRepository;
import ru.otus.spring.homework.springproject.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(
        OrderRepository orderRepository,
        BookRepository bookRepository,
        OrderMapper orderMapper
    ) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getAll() {
        log.debug("getAll()");
        return orderMapper.toDtoList(orderRepository.findAll());
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        log.debug("getOrder");
        return orderMapper.toDto(orderRepository.getOne(orderId));
    }

    @Override
    public List<OrderDto> getOrders(List<String> orderIds) {
        log.debug("getOrders");
        var ids = orderIds.stream()
            .map(Long::parseLong)
            .collect(Collectors.toList());
        return orderMapper.toDtoList(orderRepository.findAllById(ids));
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        log.debug("addOrder");
        var entity = orderMapper.toEntity(orderDto, bookRepository);
        return orderMapper.toDto(orderRepository.save(entity));
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        log.debug("updateOrder");
        return orderMapper.toDto(orderRepository.save(orderMapper.toEntity(orderDto, bookRepository)));
    }

    @Override
    public void deleteOrder(Long orderId) {
        log.debug("deleteOrder");
        orderRepository.deleteById(orderId);
    }

    @Override
    public long getCount() {
        log.debug("getCount");
        return orderRepository.count();
    }
}
