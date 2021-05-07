package ru.otus.spring.homework.springproject.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.springproject.models.dto.BasketDto;
import ru.otus.spring.homework.springproject.service.BasketService;

@Slf4j
@RestController
public class BasketController {
    private static final String BASKET_SERVICE = "basketService";

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    // Получить корзину по id
    @GetMapping("/api/basket/{id}")
    @Bulkhead(name = BASKET_SERVICE, fallbackMethod = "bulkHeadGetBasket", type = Type.SEMAPHORE)
    public ResponseEntity<BasketDto> getBasket(@PathVariable("id") Long basketId) {
        var basket = basketService.getBasket(basketId);
        if (basket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(basket, HttpStatus.OK);
    }

    // Добавить книгу в корзину
    @PostMapping("/api/basket/{id}/add/{bookId}")
    @Bulkhead(name = BASKET_SERVICE, fallbackMethod = "bulkHeadGetBasket", type = Type.SEMAPHORE)
    public ResponseEntity<BasketDto> addBookIntoBasket(@PathVariable("id") Long id, @PathVariable("bookId") Long bookId) {
        var basket = basketService.addBook(id, bookId);
        if (basket == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(basket, HttpStatus.OK);
    }

    // Удалить книгу из корзины
    @PostMapping("/api/basket/{id}/remove/{bookId}")
    @Bulkhead(name = BASKET_SERVICE, fallbackMethod = "bulkHeadGetBasket", type = Type.SEMAPHORE)
    public ResponseEntity<BasketDto> removeBookFromBasket(@PathVariable("id") Long id, @PathVariable("bookId") Long bookId) {
        var basket = basketService.removeBook(id, bookId);
        if (basket == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(basket, HttpStatus.OK);
    }

    // Очистить корзину
    @DeleteMapping("/api/basket/{id}")
    @Bulkhead(name = BASKET_SERVICE, fallbackMethod = "bulkHeadDelBasket", type = Type.SEMAPHORE)
    public ResponseEntity<BasketDto> clearBasket(@PathVariable("id") Long basketId) {
        var basket = basketService.clearBasket(basketId);
        if (basket == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(basket, HttpStatus.OK);
    }

    public ResponseEntity<BasketDto> bulkHeadGetBasket(Exception t) {
        log.info("bulkHeadGetBasket");
        var author = new BasketDto();
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadDelBasket(Exception t) {
        log.info("bulkHeadDelBasket");
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
