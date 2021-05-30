package ru.otus.spring.homework.springproject.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    // Получить заказ
    @GetMapping("/api/order/{id}")
    @Bulkhead(name = ORDER_SERVICE, fallbackMethod = "bulkHeadGetOrder", type = Type.SEMAPHORE)
    public ResponseEntity<OrderDto> getOrders(@PathVariable("id") Long orderId) {
        if (orderId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        OrderDto order;
        if (orderId.equals(0L)) {
            order = new OrderDto();
            order.setId("0");
        } else {
            order = orderService.getOrder(orderId);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // Создать новый заказ на базе корзины
    @PostMapping("/api/order/{basketId}")
    @Bulkhead(name = ORDER_SERVICE, fallbackMethod = "bulkHeadGetOrder", type = Type.SEMAPHORE)
    public ResponseEntity<OrderDto> addOrderByBasketId(@PathVariable("basketId") Long basketId) {
        if (basketId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var order = orderService.createByBasketId(basketId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // Создать новый заказ
    @PostMapping("/api/order")
    @Bulkhead(name = ORDER_SERVICE, fallbackMethod = "bulkHeadGetOrder", type = Type.SEMAPHORE)
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        if (orderDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        orderDto.setId("0");
        var order = orderService.addOrder(orderDto);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // Обновить заказ
    @PutMapping("/api/order/{id}")
    @Bulkhead(name = ORDER_SERVICE, fallbackMethod = "bulkHeadGetOrder", type = Type.SEMAPHORE)
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") Long id, @RequestBody OrderDto orderDto) {
        if (orderDto == null || orderDto.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        orderDto.setId(Long.toString(id));
        var order = orderService.getOrder(Long.parseLong(orderDto.getId()));
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order = orderService.updateOrder(orderDto);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // Удалить заказ
    @DeleteMapping("/api/order/{id}")
    @Bulkhead(name = ORDER_SERVICE, fallbackMethod = "bulkHeadDelOrder", type = Type.SEMAPHORE)
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long orderId) {
        if (orderId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var order = orderService.getOrder(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<OrderDto>> bulkHeadGetAllOrder(Exception t) {
        log.info("bulkHeadGetAllOrder");
        List<OrderDto> Order = new ArrayList<>();
        Order.add(new OrderDto("1", "I100", LocalDateTime.now(), OrderStatus.OPEN, List.of(), "1"));
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }

    public ResponseEntity<OrderDto> bulkHeadGetOrder(Exception t) {
        log.info("bulkHeadGetOrder");
        var Order = new OrderDto("1", "I100", LocalDateTime.now(), OrderStatus.OPEN, List.of(), "1");
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadDelOrder(Exception t) {
        log.info("bulkHeadDelOrder");
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
