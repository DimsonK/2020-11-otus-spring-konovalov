# Домашнее задание к занятию Spring WebFlux

Занятие 20

## Классы

* Root
    * **[Main](src/main/java/ru/otus/spring/homework/spring11/Main.java)** - основной класс приложения
* Domain
  * **[Author](src/main/java/ru/otus/spring/homework/spring11/models/entity/Author.java)** - модель автора
  * **[Book](src/main/java/ru/otus/spring/homework/spring11/models/entity/Book.java)** - модель книги
  * **[Comment](src/main/java/ru/otus/spring/homework/spring11/models/entity/Comment.java)** - модель комментария
  * **[Genre](src/main/java/ru/otus/spring/homework/spring11/models/entity/Genre.java)** - модель жанра
* DTO
  * **[AuthorDto](src/main/java/ru/otus/spring/homework/spring11/models/dto/AuthorDto.java)** - dto автора
  * **[BookDto](src/main/java/ru/otus/spring/homework/spring11/models/dto/BookDto.java)** - dto книги
  * **[CommentDto](src/main/java/ru/otus/spring/homework/spring11/models/dto/CommentDto.java)** - dto комментария
  * **[GenreDto](src/main/java/ru/otus/spring/homework/spring11/models/dto/GenreDto.java)** - dto жанра
* Mappers
  * **[AuthorMapper](src/main/java/ru/otus/spring/homework/spring11/mappers/AuthorMapper.java)** - mapper автора
  * **[BookMapper](src/main/java/ru/otus/spring/homework/spring11/mappers/BookMapper.java)** - mapper книги
  * **[CommentMapper](src/main/java/ru/otus/spring/homework/spring11/mappers/CommentMapper.java)** - mapper комментария
  * **[GenreMapper](src/main/java/ru/otus/spring/homework/spring11/mappers/GenreMapper.java)** - mapper жанра
* Dao
  * **[Author Repository](src/main/java/ru/otus/spring/homework/spring11/repositories/AuthorRepository.java)** - интерфейс Author Repository
  * **[Book Repository](src/main/java/ru/otus/spring/homework/spring11/repositories/BookRepository.java)** - интерфейс Book Repository
  * **[Book Repository Custom](src/main/java/ru/otus/spring/homework/spring11/repositories/BookRepositoryCustom.java)** - интерфейс Book Repository Custom
  * **[Book Repository Custom Impl](src/main/java/ru/otus/spring/homework/spring11/repositories/BookRepositoryCustomImpl.java)** - реализация Book Repository Custom 
  * **[Comment Repository](src/main/java/ru/otus/spring/homework/spring11/repositories/CommentRepository.java)** - интерфейс Comment Repository      
  * **[Comment Repository Custom](src/main/java/ru/otus/spring/homework/spring11/repositories/CommentRepositoryCustom.java)** - интерфейс Comment Repository Custom    
  * **[Comment Repository Custom Impl](src/main/java/ru/otus/spring/homework/spring11/repositories/CommentRepositoryCustomImpl.java)** - реализация Comment Repository Custom    
  * **[Genre Repository](src/main/java/ru/otus/spring/homework/spring11/repositories/GenreRepository.java)** - интерфейс Genre Repository
* Controllers
  * **[AuthorController](src/main/java/ru/otus/spring/homework/spring11/controllers/AuthorController.java)** - контроллер для автора
  * **[BookController](src/main/java/ru/otus/spring/homework/spring11/controllers/BookController.java)** - контроллер для книги
  * **[CommentController](src/main/java/ru/otus/spring/homework/spring11/controllers/CommentController.java)** - контроллер для комментария
  * **[GenreController](src/main/java/ru/otus/spring/homework/spring11/controllers/GenreController.java)** - контроллер для жанра
  
## Ресурсы

* **[application.yml](src/main/resources/application.yml)** - конфигурация приложения
* **[banner.txt](src/main/resources/banner.txt)** - баннер
* **[public](src/main/resources/public)** - web ресурсы

## Сборка

`mvn clean package`

В результате сборки формируется два jar файла:

**./target/spring-11-0.0.1.jar** - содержит встроенные библиотеки, можно запускать
`java -Dfile.encoding=utf-8 -jar spring-11-0.0.1.jar`

`-Dfile.encoding=utf-8` - необходимо для корректного отображения русских букв в консоли, в Windows запускать рекомендуется в консоли `GitBash`. 
  
После запуска клиент Angular доступен по адресу `http://localhost:8080/`

## Запуск в режиме разработки

* в системе должен быть установлен NodeJs и Angular Cli `npm install -g @angular/cli`

1. `mvn clean package`
2. `java -Dfile.encoding=utf-8 -jar ./target/spring-11-0.0.1.jar`
3. `ng serve --open`

В результате откроется окно браузера по умолчанию по адресу: `http://localhost:4200/`, серверная часть будет доступна по адресу: `http://localhost:8080/`