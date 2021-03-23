package ru.otus.spring.homework.spring15.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring15.domain.Invoice;
import ru.otus.spring.homework.spring15.utils.RandomService;

@Slf4j
@Service
public class WarehouseService {

    private final RandomService randomService;

    public WarehouseService(RandomService randomService) {
        this.randomService = randomService;
    }

    public Invoice collectInvoice(Invoice invoice) {
        try {
            log.info(String.format("Начало сборки заказа: %s", invoice.getOrderNumber()));
            Thread.sleep(randomService.getRandomInt(100, invoice.getProductItems().size() * 500));
            log.info(String.format("Заказ %s собран", invoice.getOrderNumber()));
            return invoice;
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return null;
    }
}
