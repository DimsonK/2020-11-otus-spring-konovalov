package ru.otus.spring.homework.spring15.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring15.config.AppConfig;
import ru.otus.spring.homework.spring15.domain.*;
import ru.otus.spring.homework.spring15.utils.RandomService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CustomerService {

    private final RandomService randomService;

    public CustomerService(
            RandomService randomService
    ) {
        this.randomService = randomService;
    }

    public Order order() {
        log.info("Начинаем заказ");
        var goodList = AppConfig.GOOD_LIST;
        int posNum = randomService.getRandomInt(1, 10);
        var orderNumber = randomService.getRandomAlNumber(5);
        List<OrderItem> orderGoods = new ArrayList<>();
        for (int i = 0; i < posNum; i++) {
            Good good = goodList.get(randomService.getRandomInt(0, goodList.size()));
            OrderItem item = new OrderItem(good, randomService.getRandomInt(1, 20), orderNumber);
            if (!orderGoods.contains(item)) {
                orderGoods.add(item);
            }
        }
        log.info(String.format("Заказали %s товаров", orderGoods.size()));
        return new Order(orderGoods, orderNumber);
    }

    public Invoice obtaining(Invoice invoice) {
        try {
            log.info(String.format("Начали получение товара по накладной: %s", invoice.getOrderNumber()));
            Thread.sleep(200);
            log.info(String.format("Получили товар по накладной: %s", invoice.getOrderNumber()));
            return invoice;
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public boolean checkDeliveryOrder(Order order, Invoice invoice) {
        boolean ret = false;
        if (!order.getNumber().equals(invoice.getOrderNumber())) {
            return false;
        }
        for (OrderItem orderItem : order.getOrderItems()) {
            boolean found = false;
            for (InvoiceItem invoiceItem : invoice.getProductItems()) {
                if (orderItem.getGood().equals(invoiceItem.getGood()) &&
                orderItem.getQuantity().equals(invoiceItem.getQuantity())) {
                    found = true;
                }
                if (!found) {
                    ret = false;
                    break;
                } else {
                    ret = true;
                }
            }
        }
        return ret;
    }

}
