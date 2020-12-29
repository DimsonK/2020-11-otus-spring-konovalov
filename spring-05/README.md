# Домашнее задание к занятию 'DAO на Spring JDBC'

Занятие 7

## Классы

* Root
    * **[Main](src/main/java/ru/otus/spring/homework/spring05/Main.java)** - основной класс приложения
* Dao
    * **[AuthorDao](src/main/java/ru/otus/spring/homework/spring05/dao/AuthorDao.java)** - интерфейс AuthorDao
    * **[AuthorDaoJdbc](src/main/java/ru/otus/spring/homework/spring05/dao/AuthorDaoJdbc.java)** - реализация AuthorDao jdbc 
    * **[BookDao](src/main/java/ru/otus/spring/homework/spring05/dao/BookDao.java)** - интерфейс BookDao
    * **[BookDaoJdbc](src/main/java/ru/otus/spring/homework/spring05/dao/BookDaoJdbc.java)** - реализация BookDao jdbc 
    * **[GenreDao](src/main/java/ru/otus/spring/homework/spring05/dao/GenreDao.java)** - интерфейс GenreDao
    * **[GenreDaoJdbc](src/main/java/ru/otus/spring/homework/spring05/dao/GenreDaoJdbc.java)** - реализация GenreDao jdbc
* Domain
    * **[Author](src/main/java/ru/otus/spring/homework/spring05/domain/Author.java)** - модель автора
    * **[Book](src/main/java/ru/otus/spring/homework/spring05/domain/Book.java)** - модель книги
    * **[Genre](src/main/java/ru/otus/spring/homework/spring05/domain/Genre.java)** - модель жанра   
* Service
    * **[AuthorService](src/main/java/ru/otus/spring/homework/spring05/service/AuthorService.java)** - интерфейс сервиса автора
    * **[AuthorServiceImpl](src/main/java/ru/otus/spring/homework/spring05/service/AuthorServiceImpl.java)** - реализация сервиса автора
    * **[BookService](src/main/java/ru/otus/spring/homework/spring05/service/BookService.java)** - интерфейс сервиса книги
    * **[BookServiceImpl](src/main/java/ru/otus/spring/homework/spring05/service/BookServiceImpl.java)** - реализация сервиса книги
    * **[GenreService](src/main/java/ru/otus/spring/homework/spring05/service/GenreService.java)** - интерфейс сервиса жанра
    * **[GenreServiceImpl](src/main/java/ru/otus/spring/homework/spring05/service/GenreServiceImpl.java)** - реализация сервиса жанра
* Shell
    * **[AuthorShellCommands](src/main/java/ru/otus/spring/homework/spring05/shell/AuthorShellCommands.java)** - команды шелла для автора
    * **[BookShellCommands](src/main/java/ru/otus/spring/homework/spring05/shell/BookShellCommands.java)** - команды шелла для книги
    * **[CustomShellCommands](src/main/java/ru/otus/spring/homework/spring05/shell/CustomShellQuit.java)** - переопределенные команды шелла
    * **[GenreShellCommands](src/main/java/ru/otus/spring/homework/spring05/shell/GenreShellCommands.java)** - команды шелла для жанра
    * **[ShellReader](src/main/java/ru/otus/spring/homework/spring05/shell/ShellReader.java)** - интерфейс ридера шелла 
    * **[ShellReaderImpl](src/main/java/ru/otus/spring/homework/spring05/shell/ShellReaderImpl.java)** - реализация ридера шелла

## Ресурсы

* **[application.yml](src/main/resources/application.yml)** - настройка приложения
* **[banner.txt](src/main/resources/banner.txt)** - баннер

## Сборка

В результате сборки формируется два jar файла:

**./target/spring-05-0.0.1.jar** - содержит встроенные библиотеки, можно запускать
`java -Dfile.encoding=utf-8 -jar spring-05-0.0.1.jar`

`-Dfile.encoding=utf-8` - необходимо для корректного отображения русских букв в консоли, в Windows запускать рекомендуется в консоли `GitBash`. 
  