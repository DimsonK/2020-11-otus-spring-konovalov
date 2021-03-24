package ru.otus.spring.homework.spring15.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring15.domain.InvoiceItem;
import ru.otus.spring.homework.spring15.domain.OrderItem;

@Slf4j
@Service
public class ManufactureService {

    public InvoiceItem produce(OrderItem orderItem) {
        try {
            log.info(String.format("Начали производство товара %s в количестве %s", orderItem.getGood().getName(), orderItem.getQuantity()));
            Thread.sleep(orderItem.getQuantity() * 100L);
            var good = orderItem.getGood();
            log.info(String.format("Закончили производство товара %s в количестве %s", orderItem.getGood().getName(), orderItem.getQuantity()));
            return new InvoiceItem(good, orderItem.getQuantity(), orderItem.getOrderNumber());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return null;
    }
}
