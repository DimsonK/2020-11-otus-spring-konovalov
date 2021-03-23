package ru.otus.spring.homework.spring15.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring15.domain.Invoice;

@Slf4j
@Service
public class DeliveryService {

    public Invoice delivery(Invoice invoice) {
        try {
            log.info(String.format("Начали доставку товара по накладной: %s", invoice.getOrderNumber()));
            Thread.sleep(3000);
            log.info(String.format("Загрузили товар по накладной: %s", invoice.getOrderNumber()));
            Thread.sleep(3000);
            log.info(String.format("Доставили товар по накладной: %s", invoice.getOrderNumber()));
            return invoice;
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return null;
    }
}
