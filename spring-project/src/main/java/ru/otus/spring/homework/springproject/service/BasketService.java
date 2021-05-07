package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.dto.BasketDto;

public interface BasketService {

    BasketDto getBasket(Long basketId);

    BasketDto addBook(Long bookId);

    BasketDto removeBook(Long bookId);

    void clearBasket(Long basketId);

    long getCount();
}
