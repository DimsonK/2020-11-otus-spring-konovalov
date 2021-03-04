# Домашнее задание к занятию Spring Security: Механизмы аутентификации

Занятие 23

## Классы

* Root
    * **[Main](src/main/java/ru/otus/spring/homework/spring12/Main.java)** - основной класс приложения
* Domain
  * **[Author](src/main/java/ru/otus/spring/homework/spring12/models/entity/Author.java)** - модель автора
  * **[Book](src/main/java/ru/otus/spring/homework/spring12/models/entity/Book.java)** - модель книги
  * **[Comment](src/main/java/ru/otus/spring/homework/spring12/models/entity/Comment.java)** - модель комментария
  * **[Genre](src/main/java/ru/otus/spring/homework/spring12/models/entity/Genre.java)** - модель жанра
* DTO
  * **[AuthorDto](src/main/java/ru/otus/spring/homework/spring12/models/dto/AuthorDto.java)** - dto автора
  * **[BookDto](src/main/java/ru/otus/spring/homework/spring12/models/dto/BookDto.java)** - dto книги
  * **[CommentDto](src/main/java/ru/otus/spring/homework/spring12/models/dto/CommentDto.java)** - dto комментария
  * **[GenreDto](src/main/java/ru/otus/spring/homework/spring12/models/dto/GenreDto.java)** - dto жанра
* Mappers
  * **[AuthorMapper](src/main/java/ru/otus/spring/homework/spring12/mappers/AuthorMapper.java)** - mapper автора
  * **[BookMapper](src/main/java/ru/otus/spring/homework/spring12/mappers/BookMapper.java)** - mapper книги
  * **[CommentMapper](src/main/java/ru/otus/spring/homework/spring12/mappers/CommentMapper.java)** - mapper комментария
  * **[GenreMapper](src/main/java/ru/otus/spring/homework/spring12/mappers/GenreMapper.java)** - mapper жанра
* Dao
  * **[Author Repository](src/main/java/ru/otus/spring/homework/spring12/repositories/AuthorRepository.java)** - интерфейс Author Repository
  * **[Book Repository](src/main/java/ru/otus/spring/homework/spring12/repositories/BookRepository.java)** - интерфейс Book Repository
  * **[Comment Repository](src/main/java/ru/otus/spring/homework/spring12/repositories/CommentRepository.java)** - интерфейс Comment Repository      
  * **[Genre Repository](src/main/java/ru/otus/spring/homework/spring12/repositories/GenreRepository.java)** - интерфейс Genre Repository
* Service
  * **[AuthorService](src/main/java/ru/otus/spring/homework/spring12/service/AuthorService.java)** - интерфейс сервиса автора
  * **[AuthorServiceImpl](src/main/java/ru/otus/spring/homework/spring12/service/AuthorServiceImpl.java)** - реализация сервиса автора
  * **[BookService](src/main/java/ru/otus/spring/homework/spring12/service/BookService.java)** - интерфейс сервиса книги
  * **[BookServiceImpl](src/main/java/ru/otus/spring/homework/spring12/service/BookServiceImpl.java)** - реализация сервиса книги
  * **[CommentService](src/main/java/ru/otus/spring/homework/spring12/service/CommentService.java)** - интерфейс сервиса комментария
  * **[CommentServiceImpl](src/main/java/ru/otus/spring/homework/spring12/service/CommentServiceImpl.java)** - реализация сервиса комментария
  * **[GenreService](src/main/java/ru/otus/spring/homework/spring12/service/GenreService.java)** - интерфейс сервиса жанра
  * **[GenreServiceImpl](src/main/java/ru/otus/spring/homework/spring12/service/GenreServiceImpl.java)** - реализация сервиса жанра
* Controllers
  * **[AuthorController](src/main/java/ru/otus/spring/homework/spring12/controllers/AuthorController.java)** - контроллер для автора
  * **[BookController](src/main/java/ru/otus/spring/homework/spring12/controllers/BookController.java)** - контроллер для книги
  * **[CommentController](src/main/java/ru/otus/spring/homework/spring12/controllers/CommentController.java)** - контроллер для комментария
  * **[GenreController](src/main/java/ru/otus/spring/homework/spring12/controllers/GenreController.java)** - контроллер для жанра
* Security
  * **[AuthProvider](src/main/java/ru/otus/spring/homework/spring12/security/AuthProvider.java)** - провайдер аутентификации с помощью БД
  * **[AuthRequest](src/main/java/ru/otus/spring/homework/spring12/security/AuthRequest.java)** - dao запроса на аутентификацию
  * **[AuthService](src/main/java/ru/otus/spring/homework/spring12/security/AuthService.java)** - сервис аутентификации
  * **[SecurityConfiguration](src/main/java/ru/otus/spring/homework/spring12/security/SecurityConfiguration.java)** - конфигурация безопасности

  
## Ресурсы

* **[application.yml](src/main/resources/application.yml)** - настройка приложения
* **[banner.txt](src/main/resources/banner.txt)** - баннер
* **[data.sql](src/main/resources/data.sql)** - дынные
* **[data.sql](src/main/resources/schema.sql)** - схема
* **[public](src/main/resources/public)** - web ресурсы, собранный проект Angular

## Сборка

`mvn clean package`

В результате сборки формируется два jar файла:

**./target/spring-12-0.0.1.jar** - содержит встроенные библиотеки, можно запускать
`java -Dfile.encoding=utf-8 -jar spring-12-0.0.1.jar`

`-Dfile.encoding=utf-8` - необходимо для корректного отображения русских букв в консоли, в Windows запускать рекомендуется в консоли `GitBash`. 
  
После запуска клиент Angular доступен по адресу `http://localhost:8080/`

## Запуск в режиме разработки

* в системе должен быть установлен NodeJs и Angular Cli `npm install -g @angular/cli`

1. `mvn clean package`
2. `java -Dfile.encoding=utf-8 -jar ./target/spring-12-0.0.1.jar`
3. `ng serve --open`

В результате откроется окно браузера по умолчанию по адресу: `http://localhost:4200/`, серверная часть будет доступна по адресу: `http://localhost:8080/`
