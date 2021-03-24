package ru.otus.spring.homework.spring15.integration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.homework.spring15.domain.Invoice;
import ru.otus.spring.homework.spring15.domain.InvoiceItem;
import ru.otus.spring.homework.spring15.domain.Order;
import ru.otus.spring.homework.spring15.domain.OrderItem;
import ru.otus.spring.homework.spring15.services.CustomerService;
import ru.otus.spring.homework.spring15.services.DeliveryService;
import ru.otus.spring.homework.spring15.services.ManufactureService;
import ru.otus.spring.homework.spring15.services.WarehouseService;

import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@EnableAutoConfiguration
@IntegrationComponentScan
@ComponentScan
@Configuration
@EnableIntegration
public class IntegrationConfig {

    private final ManufactureService manufactureService;
    private final WarehouseService warehouseService;
    private final DeliveryService deliveryService;
    private final CustomerService customerService;

    public IntegrationConfig(
            ManufactureService manufactureService,
            WarehouseService warehouseService,
            DeliveryService deliveryService,
            CustomerService customerService
    ) {
        this.manufactureService = manufactureService;
        this.warehouseService = warehouseService;
        this.deliveryService = deliveryService;
        this.customerService = customerService;
    }

    @MessagingGateway
    public interface CompanySales {
        @Gateway(requestChannel = "ordersChannel", replyChannel = "invoicesChannel")
        Invoice saleFlow(Order order);
    }

    @Bean
    public QueueChannel ordersChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel invoicesChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow orders() {
        return IntegrationFlows.from("ordersChannel")
                .split(Order.class, Order::getOrderItems)
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .<OrderItem, InvoiceItem>transform(manufactureService::produce)
                .aggregate(aggregator -> aggregator
                        .outputProcessor(g -> {
                            var orderNumber = g.getMessages().stream()
                                    .map(message -> {
                                        var payload = (InvoiceItem) message.getPayload();
                                        return payload.getOrderNumber();
                                    }).findFirst().orElse(null);

                            return new Invoice(
                                    g.getMessages().stream()
                                            .map(message -> (InvoiceItem) message.getPayload())
                                            .collect(Collectors.toList()), orderNumber);
                        })
                        .correlationStrategy(m -> ((InvoiceItem) m.getPayload()).getOrderNumber())
                )
                .transform(warehouseService::collectInvoice)
                .transform(deliveryService::delivery)
                .transform(customerService::obtaining)
                .channel("invoicesChannel")
                .get();
    }

}
