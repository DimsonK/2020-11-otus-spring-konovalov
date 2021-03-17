# Домашнее задание к занятию Spring Batch

Занятие 26

## Классы

* Root
  * **[Main](src/main/java/ru/otus/spring/homework/spring14/Main.java)** - основной класс приложения
* Mongock changelogs
  * **[InitMongoDBDataChangeLog](src/main/java/ru/otus/spring/homework/spring14/changelogs/InitMongoDBDataChangeLog.java)** - подготовка данных MongoDb
* Config
  * **[BatchConfig](src/main/java/ru/otus/spring/homework/spring14/config/BatchConfig.java)** - конфигурация Spring Batch
  * **[JobConfig](src/main/java/ru/otus/spring/homework/spring14/config/JobConfig.java)** - конфигурация Job Spring Batch
* Model H2
  * **[AuthorDao](src/main/java/ru/otus/spring/homework/spring14/model/h2/AuthorDao.java)** - модель h2 автора
  * **[BookDao](src/main/java/ru/otus/spring/homework/spring14/model/h2/BookDao.java)** - модель h2 книги
  * **[BookGenresDao](src/main/java/ru/otus/spring/homework/spring14/model/h2/BookGenresDao.java)** - модель h2 связи книги с жанрами 
  * **[CommentDao](src/main/java/ru/otus/spring/homework/spring14/model/h2/CommentDao.java)** - модель h2 комментария
  * **[GenreDao](src/main/java/ru/otus/spring/homework/spring14/model/h2/GenreDao.java)** - модель h2 жанра
* Model Mongo
  * **[Author](src/main/java/ru/otus/spring/homework/spring14/model/mongo/Author.java)** - модель Mongo автора
  * **[Book](src/main/java/ru/otus/spring/homework/spring14/model/mongo/Book.java)** - модель Mongo книги
  * **[Comment](src/main/java/ru/otus/spring/homework/spring14/model/mongo/Comment.java)** - модель Mongo комментария
  * **[Genre](src/main/java/ru/otus/spring/homework/spring14/model/mongo/Genre.java)** - модель Mongo жанра
* Service
  * **[SequenceService](src/main/java/ru/otus/spring/homework/spring14/service/SequenceService.java)** - сервиса генерации новых ID автора
* Shell
  * **[BatchCommands](src/main/java/ru/otus/spring/homework/spring14/shell/BatchCommands.java)** - команды shell
  * **[CustomShellQuit](src/main/java/ru/otus/spring/homework/spring14/shell/CustomShellQuit.java)** - shell класс выхода из приложения
  
## Ресурсы

* **[application.yml](src/main/resources/application.yml)** - настройка приложения
* **[data.sql](src/main/resources/schema.sql)** - схема БД H2
