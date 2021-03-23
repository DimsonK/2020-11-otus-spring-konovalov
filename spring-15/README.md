# Домашнее задание к занятию Spring Integration: Endpoints и Flow Components

Занятие 29

## Классы

* Root
  * **[Main](src/main/java/ru/otus/spring/homework/spring15/Main.java)** - основной класс приложения
* Config
  * **[AppConfig](src/main/java/ru/otus/spring/homework/spring15/config/AppConfig.java)** - конфигурация приложения
* Domain
  * **[Good](src/main/java/ru/otus/spring/homework/spring15/domain/Good.java)** - модель товара
  * **[Invoice](src/main/java/ru/otus/spring/homework/spring15/domain/Invoice.java)** - модель накладной
  * **[InvoiceItem](src/main/java/ru/otus/spring/homework/spring15/domain/InvoiceItem.java)** - модель товарной позиции накладной 
  * **[Order](src/main/java/ru/otus/spring/homework/spring15/domain/Order.java)** - модель счета
  * **[OrderItem](src/main/java/ru/otus/spring/homework/spring15/domain/OrderItem.java)** - модель товарной позиции счета
* Integration
  * **[IntegrationConfig](src/main/java/ru/otus/spring/homework/spring15/integration/IntegrationConfig.java)** - настройка Spring Integration
* Service
  * **[CustomerService](src/main/java/ru/otus/spring/homework/spring15/services/CustomerService.java)** - сервис покупателя
  * **[DeliveryService](src/main/java/ru/otus/spring/homework/spring15/services/DeliveryService.java)** - сервис доставки
  * **[ManufactureService](src/main/java/ru/otus/spring/homework/spring15/services/ManufactureService.java)** - сервис производства
  * **[WarehouseService](src/main/java/ru/otus/spring/homework/spring15/services/WarehouseService.java)** - сервис складирования
* Utils
  * **[RandomService](src/main/java/ru/otus/spring/homework/spring15/utils/RandomService.java)** - генератор
  * **[ScheduledTasks](src/main/java/ru/otus/spring/homework/spring15/utils/ScheduledTasks.java)** - запуск по таймеру
  
## Ресурсы

* **[application.yml](src/main/resources/application.yml)** - настройка приложения
