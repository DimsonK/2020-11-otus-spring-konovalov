package ru.otus.spring.homework.springproject.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.homework.springproject.models.dto.OrderDto;
import ru.otus.spring.homework.springproject.models.enums.OrderStatus;
import ru.otus.spring.homework.springproject.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class OrderController {

    private static final String ORDER_SERVICE = "orderService";

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Получить все заказы
    @GetMapping("/api/order")
    @Bulkhead(name = ORDER_SERVICE, fallbackMethod = "bulkHeadGetAllOrder", type = Type.SEMAPHORE)
    public ResponseEntity<List<OrderDto>> getOrders() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<OrderDto>> bulkHeadGetAllOrder(Exception t) {
        log.info("bulkHeadGetAllOrder");
        List<OrderDto> Order = new ArrayList<>();
        Order.add(new OrderDto("1", "I100", LocalDateTime.now(), OrderStatus.OPEN, List.of(), "1"));
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }

    public ResponseEntity<OrderDto> bulkHeadGetOrder(Exception t) {
        log.info("bulkHeadGetOrder");
        var Order = new OrderDto("1", "I100", LocalDateTime.now(), OrderStatus.OPEN, List.of(),"1");
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadDelOrder(Exception t) {
        log.info("bulkHeadDelOrder");
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
