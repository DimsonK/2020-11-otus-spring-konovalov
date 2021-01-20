# Домашнее задание к занятию "Белая магия" Spring Data: Spring Data JPA

Занятие 11

## Классы

* Root
    * **[Main](src/main/java/ru/otus/spring/homework/spring07/Main.java)** - основной класс приложения
* Domain
  * **[Author](src/main/java/ru/otus/spring/homework/spring07/models/Author.java)** - модель автора
  * **[Book](src/main/java/ru/otus/spring/homework/spring07/models/Book.java)** - модель книги
  * **[Comment](src/main/java/ru/otus/spring/homework/spring07/models/Comment.java)** - модель комментария
  * **[Genre](src/main/java/ru/otus/spring/homework/spring07/models/Genre.java)** - модель жанра
* Dao
  * **[Author Repository](src/main/java/ru/otus/spring/homework/spring07/repositories/AuthorRepository.java)** - интерфейс Author Repository
  * **[Book Repository](src/main/java/ru/otus/spring/homework/spring07/repositories/BookRepository.java)** - интерфейс Book Repository
  * **[Comment Repository](src/main/java/ru/otus/spring/homework/spring07/repositories/CommentRepository.java)** - интерфейс Comment Repository      
  * **[Genre Repository](src/main/java/ru/otus/spring/homework/spring07/repositories/GenreRepository.java)** - интерфейс Genre Repository
* Service
  * **[AuthorService](src/main/java/ru/otus/spring/homework/spring07/service/AuthorService.java)** - интерфейс сервиса автора
  * **[AuthorServiceImpl](src/main/java/ru/otus/spring/homework/spring07/service/AuthorServiceImpl.java)** - реализация сервиса автора
  * **[BookService](src/main/java/ru/otus/spring/homework/spring07/service/BookService.java)** - интерфейс сервиса книги
  * **[BookServiceImpl](src/main/java/ru/otus/spring/homework/spring07/service/BookServiceImpl.java)** - реализация сервиса книги
  * **[CommentService](src/main/java/ru/otus/spring/homework/spring07/service/CommentService.java)** - интерфейс сервиса комментария
  * **[CommentServiceImpl](src/main/java/ru/otus/spring/homework/spring07/service/CommentServiceImpl.java)** - реализация сервиса комментария
  * **[GenreService](src/main/java/ru/otus/spring/homework/spring07/service/GenreService.java)** - интерфейс сервиса жанра
  * **[GenreServiceImpl](src/main/java/ru/otus/spring/homework/spring07/service/GenreServiceImpl.java)** - реализация сервиса жанра
* Shell
  * **[AuthorShellCommands](src/main/java/ru/otus/spring/homework/spring07/shell/AuthorShellCommands.java)** - команды шелла для автора
  * **[BookShellCommands](src/main/java/ru/otus/spring/homework/spring07/shell/BookShellCommands.java)** - команды шелла для книги
  * **[CommentShellCommands](src/main/java/ru/otus/spring/homework/spring07/shell/CommentShellCommands.java)** - команды шелла для комментария
  * **[CustomShellCommands](src/main/java/ru/otus/spring/homework/spring07/shell/CustomShellQuit.java)** - переопределенные команды шелла
  * **[GenreShellCommands](src/main/java/ru/otus/spring/homework/spring07/shell/GenreShellCommands.java)** - команды шелла для жанра
  * **[ShellReader](src/main/java/ru/otus/spring/homework/spring07/shell/ShellReader.java)** - интерфейс ридера шелла 
  * **[ShellReaderImpl](src/main/java/ru/otus/spring/homework/spring07/shell/ShellReaderImpl.java)** - реализация ридера шелла

## Ресурсы

* **[application.yml](src/main/resources/application.yml)** - настройка приложения
* **[banner.txt](src/main/resources/banner.txt)** - баннер
* **[data.sql](src/main/resources/data.sql)** - дынные
* **[schema.sql](src/main/resources/schema.sql)** - схема дынных

## Сборка

В результате сборки формируется два jar файла:

**./target/spring-07-0.0.1.jar** - содержит встроенные библиотеки, можно запускать
`java -Dfile.encoding=utf-8 -jar spring-07-0.0.1.jar`

`-Dfile.encoding=utf-8` - необходимо для корректного отображения русских букв в консоли, в Windows запускать рекомендуется в консоли `GitBash`. 
  