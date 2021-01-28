# Домашнее задание к занятию Spring Data для подключения к нереляционным БД

Занятие 13

## Классы

* Root
    * **[Main](src/main/java/ru/otus/spring/homework/spring08/Main.java)** - основной класс приложения
* Domain
  * **[Author](src/main/java/ru/otus/spring/homework/spring08/models/Author.java)** - модель автора
  * **[Book](src/main/java/ru/otus/spring/homework/spring08/models/Book.java)** - модель книги
  * **[Comment](src/main/java/ru/otus/spring/homework/spring08/models/Comment.java)** - модель комментария
  * **[Genre](src/main/java/ru/otus/spring/homework/spring08/models/Genre.java)** - модель жанра
* Dao
  * **[Author Repository](src/main/java/ru/otus/spring/homework/spring08/repositories/AuthorRepository.java)** - интерфейс Author Repository
  * **[Book Repository](src/main/java/ru/otus/spring/homework/spring08/repositories/BookRepository.java)** - интерфейс Book Repository
  * **[Book Repository Custom](src/main/java/ru/otus/spring/homework/spring08/repositories/BookRepositoryCustom.java)** - интерфейс Book Repository Custom
  * **[Book Repository Custom Impl](src/main/java/ru/otus/spring/homework/spring08/repositories/BookRepositoryCustomImpl.java)** - реализация Book Repository Custom
  * **[Comment Repository](src/main/java/ru/otus/spring/homework/spring08/repositories/CommentRepository.java)** - интерфейс Comment Repository      
  * **[Comment Repository Custom](src/main/java/ru/otus/spring/homework/spring08/repositories/CommentRepositoryCustom.java)** - интерфейс Comment Repository Custom
  * **[Comment Repository Custom Impl](src/main/java/ru/otus/spring/homework/spring08/repositories/CommentRepositoryCustomImpl.java)** - реализация Comment Repository Custom
  * **[Genre Repository](src/main/java/ru/otus/spring/homework/spring08/repositories/GenreRepository.java)** - интерфейс Genre Repository
* Service
  * **[AuthorService](src/main/java/ru/otus/spring/homework/spring08/service/AuthorService.java)** - интерфейс сервиса автора
  * **[AuthorServiceImpl](src/main/java/ru/otus/spring/homework/spring08/service/AuthorServiceImpl.java)** - реализация сервиса автора
  * **[BookService](src/main/java/ru/otus/spring/homework/spring08/service/BookService.java)** - интерфейс сервиса книги
  * **[BookServiceImpl](src/main/java/ru/otus/spring/homework/spring08/service/BookServiceImpl.java)** - реализация сервиса книги
  * **[CommentService](src/main/java/ru/otus/spring/homework/spring08/service/CommentService.java)** - интерфейс сервиса комментария
  * **[CommentServiceImpl](src/main/java/ru/otus/spring/homework/spring08/service/CommentServiceImpl.java)** - реализация сервиса комментария
  * **[GenreService](src/main/java/ru/otus/spring/homework/spring08/service/GenreService.java)** - интерфейс сервиса жанра
  * **[GenreServiceImpl](src/main/java/ru/otus/spring/homework/spring08/service/GenreServiceImpl.java)** - реализация сервиса жанра
* Shell
  * **[AuthorShellCommands](src/main/java/ru/otus/spring/homework/spring08/shell/AuthorShellCommands.java)** - команды шелла для автора
  * **[BookShellCommands](src/main/java/ru/otus/spring/homework/spring08/shell/BookShellCommands.java)** - команды шелла для книги
  * **[CommentShellCommands](src/main/java/ru/otus/spring/homework/spring08/shell/CommentShellCommands.java)** - команды шелла для комментария
  * **[CustomShellCommands](src/main/java/ru/otus/spring/homework/spring08/shell/CustomShellQuit.java)** - переопределенные команды шелла
  * **[GenreShellCommands](src/main/java/ru/otus/spring/homework/spring08/shell/GenreShellCommands.java)** - команды шелла для жанра
  * **[ShellReader](src/main/java/ru/otus/spring/homework/spring08/shell/ShellReader.java)** - интерфейс ридера шелла 
  * **[ShellReaderImpl](src/main/java/ru/otus/spring/homework/spring08/shell/ShellReaderImpl.java)** - реализация ридера шелла
* Changelogs
  * **[InitMongoDBDataChangeLog](src/main/java/ru/otus/spring/homework/spring08/changelogs/InitMongoDBDataChangeLog.java)** - Mongock changelogs

## Ресурсы

* **[application.yml](src/main/resources/application.yml)** - настройка приложения
* **[banner.txt](src/main/resources/banner.txt)** - баннер

## Сборка

В результате сборки формируется два jar файла:

**./target/spring-08-0.0.1.jar** - содержит встроенные библиотеки, можно запускать
`java -Dfile.encoding=utf-8 -jar spring-08-0.0.1.jar`

`-Dfile.encoding=utf-8` - необходимо для корректного отображения русских букв в консоли, в Windows запускать рекомендуется в консоли `GitBash`. 
  