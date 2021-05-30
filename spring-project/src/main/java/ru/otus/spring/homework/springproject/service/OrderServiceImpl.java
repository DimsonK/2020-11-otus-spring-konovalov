package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.exceptions.BadRequestException;
import ru.otus.spring.homework.springproject.mappers.BookMapper;
import ru.otus.spring.homework.springproject.mappers.OrderMapper;
import ru.otus.spring.homework.springproject.models.dto.OrderDto;
import ru.otus.spring.homework.springproject.models.entity.Order;
import ru.otus.spring.homework.springproject.models.enums.OrderStatus;
import ru.otus.spring.homework.springproject.repositories.BookRepository;
import ru.otus.spring.homework.springproject.repositories.OrderRepository;
import ru.otus.spring.homework.springproject.repositories.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final OrderMapper orderMapper;
    private final BasketService basketService;
    private final UserRepository userRepository;
    private final BookMapper bookMapper;

    public OrderServiceImpl(
        OrderRepository orderRepository,
        BookRepository bookRepository,
        OrderMapper orderMapper,
        BasketService basketService, UserRepository userRepository, BookMapper bookMapper) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.orderMapper = orderMapper;
        this.basketService = basketService;
        this.userRepository = userRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional(readOnly = true)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<OrderDto> getAll() {
        log.debug("getAll()");
        return orderMapper.toDtoList(orderRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public OrderDto getOrder(Long orderId) {
        log.debug("getOrder");
        return orderMapper.toDto(orderRepository.getOne(orderId));
    }

    @Override
    @Transactional(readOnly = true)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<OrderDto> getOrders(List<String> orderIds) {
        log.debug("getOrders");
        var ids = orderIds.stream()
            .map(Long::parseLong)
            .collect(Collectors.toList());
        return orderMapper.toDtoList(orderRepository.findAllById(ids));
    }

    @Override
    @Transactional
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public OrderDto addOrder(OrderDto orderDto) {
        log.debug("addOrder");
        var entity = orderMapper.toEntity(orderDto, bookRepository, userRepository);
        return orderMapper.toDto(orderRepository.save(entity));
    }

    @Override
    @Transactional
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public OrderDto createByBasketId(Long basketId) {
        log.debug("createByBasketId");
        var basket = basketService.getBasket(basketId);
        if (basket.getBooks().isEmpty()) {
            throw new BadRequestException("Basket is empty, order not created");
        }
        var user = userRepository.findById(basketId).orElse(null);
        var order = new Order(
            null,
            getOrderNumber(),
            LocalDateTime.now(),
            OrderStatus.OPEN,
            user,
            bookMapper.toEntityList(basket.getBooks())
        );
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public OrderDto updateOrder(OrderDto orderDto) {
        log.debug("updateOrder");
        return orderMapper.toDto(orderRepository.save(orderMapper.toEntity(orderDto, bookRepository, userRepository)));
    }

    @Override
    @Transactional
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void deleteOrder(Long orderId) {
        log.debug("deleteOrder");
        orderRepository.deleteById(orderId);
    }

    @Override
    @Transactional(readOnly = true)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public long getCount() {
        log.debug("getCount");
        return orderRepository.count();
    }

    private String getOrderNumber() {
        var date = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now());
        var rnd = RandomStringUtils.randomAlphabetic(10);
        return String.format("O-%s-%s", date, rnd);
    }
}
