# Домашнее задание к занятию Zuul, Hystrix Circuit Breaker, Sleuth, Zipkin, Hystrix Dashboard, Secure Configuration Properties

Занятие 35

18. Обернуть внешние вызовы в Hystrix

## Классы

* Root
    * **[Main](src/main/java/ru/otus/spring/homework/spring18/Main.java)** - основной класс приложения
* Domain
  * **[Author](src/main/java/ru/otus/spring/homework/spring18/models/entity/Author.java)** - модель автора
  * **[Book](src/main/java/ru/otus/spring/homework/spring18/models/entity/Book.java)** - модель книги
  * **[Comment](src/main/java/ru/otus/spring/homework/spring18/models/entity/Comment.java)** - модель комментария
  * **[Genre](src/main/java/ru/otus/spring/homework/spring18/models/entity/Genre.java)** - модель жанра
* DTO
  * **[AuthorDto](src/main/java/ru/otus/spring/homework/spring18/models/dto/AuthorDto.java)** - dto автора
  * **[BookDto](src/main/java/ru/otus/spring/homework/spring18/models/dto/BookDto.java)** - dto книги
  * **[CommentDto](src/main/java/ru/otus/spring/homework/spring18/models/dto/CommentDto.java)** - dto комментария
  * **[GenreDto](src/main/java/ru/otus/spring/homework/spring18/models/dto/GenreDto.java)** - dto жанра
* Mappers
  * **[AuthorMapper](src/main/java/ru/otus/spring/homework/spring18/mappers/AuthorMapper.java)** - mapper автора
  * **[BookMapper](src/main/java/ru/otus/spring/homework/spring18/mappers/BookMapper.java)** - mapper книги
  * **[CommentMapper](src/main/java/ru/otus/spring/homework/spring18/mappers/CommentMapper.java)** - mapper комментария
  * **[GenreMapper](src/main/java/ru/otus/spring/homework/spring18/mappers/GenreMapper.java)** - mapper жанра
* Dao
  * **[Author Repository](src/main/java/ru/otus/spring/homework/spring18/repositories/AuthorRepository.java)** - интерфейс Author Repository
  * **[Book Repository](src/main/java/ru/otus/spring/homework/spring18/repositories/BookRepository.java)** - интерфейс Book Repository
  * **[Comment Repository](src/main/java/ru/otus/spring/homework/spring18/repositories/CommentRepository.java)** - интерфейс Comment Repository      
  * **[Genre Repository](src/main/java/ru/otus/spring/homework/spring18/repositories/GenreRepository.java)** - интерфейс Genre Repository
* Service
  * **[AuthorService](src/main/java/ru/otus/spring/homework/spring18/service/AuthorService.java)** - интерфейс сервиса автора
  * **[AuthorServiceImpl](src/main/java/ru/otus/spring/homework/spring18/service/AuthorServiceImpl.java)** - реализация сервиса автора
  * **[BookService](src/main/java/ru/otus/spring/homework/spring18/service/BookService.java)** - интерфейс сервиса книги
  * **[BookServiceImpl](src/main/java/ru/otus/spring/homework/spring18/service/BookServiceImpl.java)** - реализация сервиса книги
  * **[CommentService](src/main/java/ru/otus/spring/homework/spring18/service/CommentService.java)** - интерфейс сервиса комментария
  * **[CommentServiceImpl](src/main/java/ru/otus/spring/homework/spring18/service/CommentServiceImpl.java)** - реализация сервиса комментария
  * **[GenreService](src/main/java/ru/otus/spring/homework/spring18/service/GenreService.java)** - интерфейс сервиса жанра
  * **[GenreServiceImpl](src/main/java/ru/otus/spring/homework/spring18/service/GenreServiceImpl.java)** - реализация сервиса жанра
* Controllers
  * **[AuthorController](src/main/java/ru/otus/spring/homework/spring18/controllers/AuthorController.java)** - контроллер для автора
  * **[BookController](src/main/java/ru/otus/spring/homework/spring18/controllers/BookController.java)** - контроллер для книги
  * **[CommentController](src/main/java/ru/otus/spring/homework/spring18/controllers/CommentController.java)** - контроллер для комментария
  * **[GenreController](src/main/java/ru/otus/spring/homework/spring18/controllers/GenreController.java)** - контроллер для жанра
* Security
  * **[AuthProvider](src/main/java/ru/otus/spring/homework/spring18/security/AuthProvider.java)** - провайдер аутентификации с помощью БД
  * **[AuthRequest](src/main/java/ru/otus/spring/homework/spring18/security/AuthRequest.java)** - dao запроса на аутентификацию
  * **[AuthService](src/main/java/ru/otus/spring/homework/spring18/security/AuthService.java)** - сервис аутентификации
  * **[SecurityConfiguration](src/main/java/ru/otus/spring/homework/spring18/security/SecurityConfiguration.java)** - конфигурация безопасности

  
## Ресурсы

* **[application.yml](src/main/resources/application.yml)** - настройка приложения
* **[banner.txt](src/main/resources/banner.txt)** - баннер
* **[data.sql](src/main/resources/data.sql)** - дынные
* **[data.sql](src/main/resources/schema.sql)** - схема
* **[public](src/main/resources/public)** - web ресурсы, собранный проект Angular

## Сборка

`mvn clean package`

В результате сборки формируется два jar файла:

**./target/spring-18-0.0.1.jar** - содержит встроенные библиотеки, можно запускать
`java -Dfile.encoding=utf-8 -jar spring-18-0.0.1.jar`

`-Dfile.encoding=utf-8` - необходимо для корректного отображения русских букв в консоли, в Windows запускать рекомендуется в консоли `GitBash`. 
  
После запуска клиент Angular доступен по адресу `http://localhost:8080/`

## Запуск в режиме разработки

* в системе должен быть установлен NodeJs и Angular Cli `npm install -g @angular/cli`

1. `mvn clean package`
2. `java -Dfile.encoding=utf-8 -jar ./target/spring-18-0.0.1.jar`
3. `ng serve --open`

В результате откроется окно браузера по умолчанию по адресу: `http://localhost:4200/`, серверная часть будет доступна по адресу: `http://localhost:8080/`

## Docker

### Запуск Docker compose

* `docker-compose up -d`

После старта web-клиент будет доступен по адресу: http://localhost:5000/

### Остановка Docker compose

* `docker-compose stop`

### Проверка работы Resilience4j

Запуск 200 запросов в 20 потоков

* Book Controller

  ```shell  
  seq 1 200 | xargs -I $ -n1 -P20 \
  curl --location --request GET 'http://localhost:8080/api/book' \
  --header 'Authorization: Basic YWRtaW46MDAwMA==' \
  --header 'Cookie: JSESSIONID=8DE85826EE1B479903E08C0F919AD5F8'
  ```

* Author Controller

  ```shell
  seq 1 200 | xargs -I $ -n1 -P20 \
  curl --location --request GET 'http://localhost:8080/api/author' \
  --header 'Authorization: Basic YWRtaW46MDAwMA==' \
  --header 'Cookie: JSESSIONID=DD4DF6EE83B5FD135F988C5AC38DFD1A'
  ```