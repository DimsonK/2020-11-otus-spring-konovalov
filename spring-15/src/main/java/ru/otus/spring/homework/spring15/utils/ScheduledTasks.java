package ru.otus.spring.homework.spring15.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring15.integration.IntegrationConfig.CompanySales;
import ru.otus.spring.homework.spring15.services.CustomerService;

@Slf4j
@Service
@EnableAsync
public class ScheduledTasks {

    private final CustomerService customerService;
    private final CompanySales companySales;

    public ScheduledTasks(
            CustomerService customerService,
            CompanySales companySales
    ) {
        this.customerService = customerService;
        this.companySales = companySales;
    }

    @Async("threadPoolTaskExecutor")
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now: {}", Thread.currentThread().getName());
        var order = customerService.order();
        log.info(order.toString());
        var invoice = companySales.saleFlow(order);
        log.info(String.format("Радостно получили товары по накладной %s%n%s", invoice.getOrderNumber(), invoice.toString()));
        log.info("Проверили: " + customerService.checkDeliveryOrder(order, invoice));
    }

}
