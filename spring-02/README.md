# Домашнее задание к занятию "Конфигурирование Spring-приложений"

Занятие 2

## Классы

* **Main.java** - основной класс приложения
* **./domain/Answer.java** - модель вопроса
* **./domain/Question.java** - модель ответа
* **./domain/Person.java** - студент
* **./domain/TestResult.java** - результат одного ответа на вопрос  
* **./service/CsvFileReader.java** - интерфейс ридера csv-файлов
* **./service/CsvFileReaderImpl.java** - реализация ридера csv-файлов
* **./service/StudentTestService.java** - интерфейс функционала тестов
* **./service/StudentTestServiceImpl.java** - реализация функционала тестов

## Ресурсы

* **answers.csv** - ответы на вопросы
* **questions.csv** - вопросы
* **application.properties** - настройка приложения

## Поля csv-файлов

Поля описаны в поредке слования в соответствующем файле csv

### Вопросы (questions.csv)

* id - уникальный номер вопроса
* name - содержимое вопроса

### Вопросы (answers.csv)

* id - уникальный номер вопроса
* questionId - id вопроса к которому эти ответы относятся
* name - содержимое ответа
* isCorrect ("true"/"false") - является ли ответ верным

## Сборка

В результате сборки формируется два jar файла:

* **./target/spring-02-1.0.jar** - не содержит библиотек
* **./target/spring-02-1.0-jar-with-dependencies.jar** - содержит встроенные библиотеки, можно запускать `java -jar
 spring-02-1.0-jar-with-dependencies.jar`
  