# Продукт "Bookworm"

управление библиотекой, поиск, заказ и выдача печатных изданий из фонда на руки по читательскому билету, читальный зал.

* Для читателей:
  * Регистрация и авторизация;
  * Поиск книг по авторам, названиям, жанрам, типам документов;
  * Читальный зал - чтение книг и материалов доступных в электронном виде в читальном зале или удаленно;
  * Личный кабинет читателя, история заказов их статус;
  * Отзывы о книге;
  * Заказ книг, с возможностью забрать книги в удобное время;
  * Уведомления по email, о сроке сдачи книги в библиотеку и статусе заказа книг;
* Для библиотекарей:
  * Ведение каталога изданий, авторов, жанров, типов материалов;
  * Загрузка в библиотеку электронных версий изданий
  * Установление доступа читателей к материалам хранилища (читальный зал, издания с ограниченным доступом);
  * Учет выдачи печатных изданий на руки, контроль возврата;

## Технологии, архитектура

### backend

* spring-boot-starter-data-jpa - поддержка реляционных БД
* spring-boot-starter-web - REST API
* spring-boot-starter-aop - аспекты
* spring-boot-starter-security - безопасность, аутентификация и авторизация
* resilience4j - отказоустойчивость
* flyway - миграция базы данных
* spring-boot-starter-actuator - мониторинг
* mapstruct - марринг объктов

### frontend

* Angular 11

## План

1. ~~JWT~~
2. ~~flyway~~
3. ~~swagger~~
4. ~~db audit~~
5. ~~доработать фронт для JWT авторизации~~
6. ~~расширить схему экземпляры, заказы, бинарник~~
7. контроллеры, сервисы, домены для заказов
   1. ~~Basket API~~
   2. Order API
   3. Instance API
   4. Issue API
   5. InstanceIssue API
8. расширить схему пользователей
9. доработать модель и форму редактирования книг
10. форма регистрации пользователей
11. форма управления пользователями
12. форма управления заказами
13. заказ книг из формы поиска
14. читальный зал

### Электронная онлайн библиотека "Bookworm"

Портал для чтения. Поиск книг, добавление в список для чтения. Разделение библиотеки по возрастным категориям и уровню доступа к определенной литературе.

#### Дополнительный функционал по сравнению с ДЗ

1. Добавить админку для управления пользователями и их правами
2. Расширить объектную модель для возможности добавления:
   2.1. Описания книги;
   2.2. Содержимого книги в формате EPUB;
   2.3. Возможности пользователю составлять собственный список для чтения;
   2.4. Возможности чтения книги с фронта.
