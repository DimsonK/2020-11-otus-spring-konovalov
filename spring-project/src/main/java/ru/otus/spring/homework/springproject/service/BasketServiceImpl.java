package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.mappers.BasketMapper;
import ru.otus.spring.homework.springproject.models.dto.BasketDto;
import ru.otus.spring.homework.springproject.models.entity.Basket;
import ru.otus.spring.homework.springproject.repositories.BasketRepository;
import ru.otus.spring.homework.springproject.repositories.BookRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final BookRepository bookRepository;
    private final UserService userService;
    private final BasketMapper basketMapper;

    public BasketServiceImpl(
        BasketRepository basketRepository,
        BookRepository bookRepository,
        UserService userService, BasketMapper basketMapper
    ) {
        this.basketRepository = basketRepository;
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.basketMapper = basketMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public BasketDto getBasket(Long basketId) {
        var basket = basketRepository.findById(basketId).orElse(null);
        if (Objects.isNull(basket)) {
            basket = createBasket();
        }
        return basketMapper.toDto(basket);
    }

    @Override
    @Transactional
    public BasketDto addBook(Long basketId, Long bookId) {
        var basket = basketRepository.findById(basketId).orElse(null);
        if (Objects.isNull(basket)) {
            basket = createBasket();
        }
        var book = bookRepository.findById(bookId).orElse(null);
        if (Objects.nonNull(basket) && Objects.nonNull(book)) {
            var books = basket.getBooks();
            if (!books.contains(book)) {
                books.add(book);
            }
            basket.setBooks(books);
            basketRepository.save(basket);
        }
        return basketMapper.toDto(basket);
    }

    @Override
    @Transactional
    public BasketDto removeBook(Long basketId, Long bookId) {
        var basket = basketRepository.findById(basketId).orElse(null);
        if (Objects.isNull(basket)) {
            basket = createBasket();
        }
        if (Objects.nonNull(basket)) {
            var book = bookRepository.findById(bookId).orElse(null);
            if (Objects.nonNull(book)) {
                basket.getBooks().remove(book);
                basketRepository.save(basket);
            }
        }
        return basketMapper.toDto(basket);
    }

    @Override
    @Transactional
    public BasketDto clearBasket(Long basketId) {
        var basket = basketRepository.findById(basketId).orElse(null);
        if (Objects.isNull(basket)) {
            basket = createBasket();
            return basketMapper.toDto(basket);
        }
        basket.getBooks().removeAll(basket.getBooks());
        return basketMapper.toDto(basketRepository.save(basket));
    }

    @Transactional
    public Basket createBasket() {
        Basket basket = null;
        var username = userService.getCurrentUser().orElse(null);
        var user = userService.findByUsername(username);
        if (Objects.nonNull(user)) {
            basket = basketRepository.save(new Basket(user.getId(), user, List.of()));
        }
        return basket;
    }
}
