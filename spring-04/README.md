# Домашнее задание к занятию 'Продвинутая конфигурация Spring-приложений'

Занятие 5

## Классы

* **[Main](src/main/java/ru/otus/spring/homework/Main.java)** - основной класс приложения
* **[./config/AppProp](src/main/java/ru/otus/spring/homework/config/AppProp.java)** - параметры приложения
* **[./configLocalizationConfig](src/main/java/ru/otus/spring/homework/config/LocalizationConfig.java)** - конфигурация локализации
* **[./domain/Answer](src/main/java/ru/otus/spring/homework/domain/Answer.java)** - модель вопроса
* **[./domain/Question](src/main/java/ru/otus/spring/homework/domain/Question.java)** - модель ответа
* **[./domain/Person](src/main/java/ru/otus/spring/homework/domain/Person.java)** - студент
* **[./domain/TestResult](src/main/java/ru/otus/spring/homework/domain/TestResult.java)** - результат одного ответа на вопрос  
* **[./service/CsvFileReader](src/main/java/ru/otus/spring/homework/service/CsvFileReader.java)** - интерфейс ридера csv-файлов
* **[./service/CsvFileReaderImpl](src/main/java/ru/otus/spring/homework/service/CsvFileReaderImpl.java)** - реализация ридера csv-файлов
* **[./service/LocaleService](src/main/java/ru/otus/spring/homework/service/LocaleService.java)** - интерфейс сервиса локализации 
* **[./service/LocaleServiceImpl](src/main/java/ru/otus/spring/homework/service/LocaleServiceImpl.java)** - реализация сервиса локализации
* **[./service/StudentTestService](src/main/java/ru/otus/spring/homework/service/StudentTestService.java)** - интерфейс функционала тестов
* **[./service/StudentTestServiceImpl](src/main/java/ru/otus/spring/homework/service/StudentTestServiceImpl.java)** - реализация функционала тестов

## Ресурсы

* **[./tests/answers_en.csv](src/main/resources/tests/answers_en.csv)** - ответы на вопросы (английский язык)
* **[./tests/questions_en.csv](src/main/resources/tests/questions_en.csv)** - вопросы (английский язык)
* **[./tests/answers_ru_RU.csv](src/main/resources/tests/answers_ru_RU.csv)** - ответы на вопросы (русский язык)
* **[./tests/questions_ru_RU.csv](src/main/resources/tests/questions_ru_RU.csv)** - вопросы (русский язык)
* **[application.yml](src/main/resources/application.yml)** - настройка приложения
* **[banner.txt](src/main/resources/banner.txt)** - баннер 

### Поля csv-файлов

Поля описаны в порядке следования в соответствующем файле csv

#### Вопросы (questions.csv)

* `id` - уникальный номер вопроса
* `name` - содержимое вопроса

#### Ответы (answers.csv)

* `id` - уникальный номер вопроса
* `questionId` - id вопроса к которому эти ответы относятся
* `name` - содержимое ответа
* `isCorrect` ("true"/"false") - является ли ответ верным

### Файл настройки приложения (application.yml)

* `application.lang.defLang` (Locale) - локаль по умолчанию
* `application.lang.useSystemLang` (boolean) - `true` - использовать локаль системы, если `false` используется локаль по умолчанию
* `application.passPercent` (int) - необходимый процент для прохождения тестов
* `application.store` - ресурсы тестов в группах по локализации
  * локализация (например `en` или `ru_RU`)
    * `questions` (String) - файл вопросов с путем относительно корня ресурсов
    * `answers` (String) - файл ответов с путем относительно корня ресурсов

## Сборка

В результате сборки формируется два jar файла:

**./target/spring-04-0.0.1.jar** - содержит встроенные библиотеки, можно запускать
  `java -Dfile.encoding=utf-8 -jar spring-03-0.0.1.jar` 

`-Dfile.encoding=utf-8` - необходимо для корректного отображения русских букв в консоли, в Windows запускать рекомендуется в консоли `GitBash`. 
  