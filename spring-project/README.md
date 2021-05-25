# Проектная работа. Тема: «Продукт "Bookworm" - управление библиотекой 

## Классы

* Root
    * **[Main](src/main/java/ru/otus/spring/homework/springproject/Main.java)** - основной класс приложения
* Domain
  * **[AuditModel](src/main/java/ru/otus/spring/homework/springproject/models/entity/AuditModel.java)** - модель аудита БД
  * **[AuditorAwareImpl](src/main/java/ru/otus/spring/homework/springproject/models/entity/AuditorAwareImpl.java)** - реализация аудита БД
  * **[Author](src/main/java/ru/otus/spring/homework/springproject/models/entity/Author.java)** - модель автора
  * **[Basket](src/main/java/ru/otus/spring/homework/springproject/models/entity/Basket.java)** - модель корзины    
  * **[Book](src/main/java/ru/otus/spring/homework/springproject/models/entity/Book.java)** - модель книги
  * **[BookFile](src/main/java/ru/otus/spring/homework/springproject/models/entity/BookFile.java)** - модель файлов книги    
  * **[Comment](src/main/java/ru/otus/spring/homework/springproject/models/entity/Comment.java)** - модель комментария
  * **[Genre](src/main/java/ru/otus/spring/homework/springproject/models/entity/Genre.java)** - модель жанра
  * **[Instance](src/main/java/ru/otus/spring/homework/springproject/models/entity/Instance.java)** - модель экземпляра книги    
  * **[Issue](src/main/java/ru/otus/spring/homework/springproject/models/entity/Issue.java)** - модель выдачи книг
  * **[IssueInstance](src/main/java/ru/otus/spring/homework/springproject/models/entity/IssueInstance.java)** - модель выданных экземпляров книг    
  * **[IssueInstanceId](src/main/java/ru/otus/spring/homework/springproject/models/entity/IssueInstanceId.java)** - модель id выданных экземпляров книг    
  * **[Order](src/main/java/ru/otus/spring/homework/springproject/models/entity/Order.java)** - модель заказа книг    
  * **[Role](src/main/java/ru/otus/spring/homework/springproject/models/entity/Role.java)** - модель роли    
  * **[User](src/main/java/ru/otus/spring/homework/springproject/models/entity/User.java)** - модель пользователя    
* DTO
  * **[AuthorDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/AuthorDto.java)** - dto автора
  * **[BasketDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/BasketDto.java)** - dto корзины    
  * **[BookDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/BookDto.java)** - dto книги
  * **[BookFileDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/BookFileDto.java)** - dto файлов книги    
  * **[CommentDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/CommentDto.java)** - dto комментария
  * **[GenreDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/GenreDto.java)** - dto жанра
  * **[InstanceDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/InstanceDto.java)** - dto экземпляра книги
  * **[IssueDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/IssueDto.java)** - dto выдачи книг  
  * **[IssueInstanceDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/IssueInstanceDto.java)** - dto выданных экземпляров книг
  * **[IssueInstanceStatusDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/IssueInstanceStatusDto.java)** - dto статуса выданных экземпляров книг    
  * **[OrderDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/OrderDto.java)** - dto заказа книг
  * **[RoleDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/RoleDto.java)** - dto роли
  * **[UserDto](src/main/java/ru/otus/spring/homework/springproject/models/dto/UserDto.java)** - dto пользователя
* Enums
    * **[IssueStatus](src/main/java/ru/otus/spring/homework/springproject/models/enums/IssueStatus.java)** - статусы выдачи
    * **[OrderStatus](src/main/java/ru/otus/spring/homework/springproject/models/enums/OrderStatus.java)** - статусы заказа
* Mappers
  * **[AuthorMapper](src/main/java/ru/otus/spring/homework/springproject/mappers/AuthorMapper.java)** - mapper автора
  * **[BasketMapper](src/main/java/ru/otus/spring/homework/springproject/mappers/BasketMapper.java)** - mapper корзины
  * **[BookFileMapper](src/main/java/ru/otus/spring/homework/springproject/mappers/BookFileMapper.java)** - mapper файлов книги    
  * **[BookMapper](src/main/java/ru/otus/spring/homework/springproject/mappers/BookMapper.java)** - mapper книги
  * **[CommentMapper](src/main/java/ru/otus/spring/homework/springproject/mappers/CommentMapper.java)** - mapper комментария
  * **[GenreMapper](src/main/java/ru/otus/spring/homework/springproject/mappers/GenreMapper.java)** - mapper жанра
  * **[InstanceMapper](src/main/java/ru/otus/spring/homework/springproject/mappers/InstanceMapper.java)** - mapper экземпляра    
  * **[IssueInstanceMapper](src/main/java/ru/otus/spring/homework/springproject/mappers/IssueInstanceMapper.java)** - mapper выданных экземпляров книг
  * **[IssueMapper](src/main/java/ru/otus/spring/homework/springproject/mappers/IssueMapper.java)** - mapper выдачи книг
  * **[OrderMapper](src/main/java/ru/otus/spring/homework/springproject/mappers/OrderMapper.java)** - mapper заказа книг
  * **[RoleMapper](src/main/java/ru/otus/spring/homework/springproject/mappers/RoleMapper.java)** - mapper роли
  * **[UserMapper](src/main/java/ru/otus/spring/homework/springproject/mappers/UserMapper.java)** - mapper пользователя
* Dao
  * **[Author Repository](src/main/java/ru/otus/spring/homework/springproject/repositories/AuthorRepository.java)** - интерфейс Author Repository
  * **[Basket Repository](src/main/java/ru/otus/spring/homework/springproject/repositories/BasketRepository.java)** - интерфейс Basket Repository
  * **[BookFile Repository](src/main/java/ru/otus/spring/homework/springproject/repositories/BookFileRepository.java)** - интерфейс BookFile Repository
  * **[Book Repository](src/main/java/ru/otus/spring/homework/springproject/repositories/BookRepository.java)** - интерфейс Book Repository
  * **[Comment Repository](src/main/java/ru/otus/spring/homework/springproject/repositories/CommentRepository.java)** - интерфейс Comment Repository      
  * **[Genre Repository](src/main/java/ru/otus/spring/homework/springproject/repositories/GenreRepository.java)** - интерфейс Genre Repository
  * **[Instance Repository](src/main/java/ru/otus/spring/homework/springproject/repositories/InstanceRepository.java)** - интерфейс экземпляра
  * **[IssueInstance Repository](src/main/java/ru/otus/spring/homework/springproject/repositories/IssueInstanceRepository.java)** - интерфейс выданных экземпляров книг
  * **[Issue Repository](src/main/java/ru/otus/spring/homework/springproject/repositories/IssueRepository.java)** - интерфейс выдачи книг
  * **[Order Repository](src/main/java/ru/otus/spring/homework/springproject/repositories/OrderRepository.java)** - интерфейс заказа книг
  * **[Role Repository](src/main/java/ru/otus/spring/homework/springproject/repositories/RoleRepository.java)** - интерфейс роли
  * **[User Repository](src/main/java/ru/otus/spring/homework/springproject/repositories/UserRepository.java)** - интерфейс пользователя
* Service
  * **[AuthorService](src/main/java/ru/otus/spring/homework/springproject/service/AuthorService.java)** - интерфейс сервиса автора
  * **[AuthorServiceImpl](src/main/java/ru/otus/spring/homework/springproject/service/AuthorServiceImpl.java)** - реализация сервиса автора
  * **[BasketService](src/main/java/ru/otus/spring/homework/springproject/service/BasketService.java)** - интерфейс сервиса корзины
  * **[BasketServiceImpl](src/main/java/ru/otus/spring/homework/springproject/service/BasketServiceImpl.java)** - реализация сервиса корзины    
  * **[BookFileService](src/main/java/ru/otus/spring/homework/springproject/service/BookFileService.java)** - интерфейс сервиса файлов книги
  * **[BookFileServiceImpl](src/main/java/ru/otus/spring/homework/springproject/service/BookFileServiceImpl.java)** - реализация сервиса файлов книги
  * **[BookService](src/main/java/ru/otus/spring/homework/springproject/service/BookService.java)** - интерфейс сервиса книги
  * **[BookServiceImpl](src/main/java/ru/otus/spring/homework/springproject/service/BookServiceImpl.java)** - реализация сервиса книги
  * **[CommentService](src/main/java/ru/otus/spring/homework/springproject/service/CommentService.java)** - интерфейс сервиса комментария
  * **[CommentServiceImpl](src/main/java/ru/otus/spring/homework/springproject/service/CommentServiceImpl.java)** - реализация сервиса комментария
  * **[GenreService](src/main/java/ru/otus/spring/homework/springproject/service/GenreService.java)** - интерфейс сервиса жанра
  * **[GenreServiceImpl](src/main/java/ru/otus/spring/homework/springproject/service/GenreServiceImpl.java)** - реализация сервиса жанра
  * **[InstanceService](src/main/java/ru/otus/spring/homework/springproject/service/InstanceService.java)** - интерфейс сервиса экземпляра
  * **[InstanceServiceImpl](src/main/java/ru/otus/spring/homework/springproject/service/InstanceServiceImpl.java)** - реализация сервиса экземпляра
  * **[IssueInstanceService](src/main/java/ru/otus/spring/homework/springproject/service/IssueInstanceService.java)** - интерфейс сервиса выдачи экземпляров книги
  * **[IssueInstanceServiceImpl](src/main/java/ru/otus/spring/homework/springproject/service/IssueInstanceServiceImpl.java)** - реализация сервиса выдачи экземпляров книги
  * **[IssueService](src/main/java/ru/otus/spring/homework/springproject/service/IssueService.java)** - интерфейс сервиса выдачи книг
  * **[IssueServiceImpl](src/main/java/ru/otus/spring/homework/springproject/service/IssueServiceImpl.java)** - реализация сервиса выдачи книг
  * **[OrderService](src/main/java/ru/otus/spring/homework/springproject/service/OrderService.java)** - интерфейс сервиса заказов
  * **[OrderServiceImpl](src/main/java/ru/otus/spring/homework/springproject/service/OrderServiceImpl.java)** - реализация сервиса заказов
  * **[RoleService](src/main/java/ru/otus/spring/homework/springproject/service/RoleService.java)** - интерфейс сервиса ролей
  * **[RoleServiceImpl](src/main/java/ru/otus/spring/homework/springproject/service/RoleServiceImpl.java)** - реализация сервиса ролей
  * **[UserService](src/main/java/ru/otus/spring/homework/springproject/service/UserService.java)** - интерфейс сервиса пользователей
  * **[UserServiceImpl](src/main/java/ru/otus/spring/homework/springproject/service/UserServiceImpl.java)** - реализация сервиса пользователей
* Controllers
  * **[AuthController](src/main/java/ru/otus/spring/homework/springproject/controllers/AuthController.java)** - контроллер для аутентификации
  * **[AuthorController](src/main/java/ru/otus/spring/homework/springproject/controllers/AuthorController.java)** - контроллер для автора
  * **[BasketController](src/main/java/ru/otus/spring/homework/springproject/controllers/BasketController.java)** - контроллер для корзины    
  * **[BookController](src/main/java/ru/otus/spring/homework/springproject/controllers/BookController.java)** - контроллер для книги
  * **[BookFileController](src/main/java/ru/otus/spring/homework/springproject/controllers/BookFileController.java)** - контроллер для файлов книги    
  * **[CommentController](src/main/java/ru/otus/spring/homework/springproject/controllers/CommentController.java)** - контроллер для комментария
  * **[ExceptionController](src/main/java/ru/otus/spring/homework/springproject/controllers/ExceptionController.java)** - контроллер для обработки ошибок    
  * **[GenreController](src/main/java/ru/otus/spring/homework/springproject/controllers/GenreController.java)** - контроллер для жанра
  * **[InstanceController](src/main/java/ru/otus/spring/homework/springproject/controllers/InstanceController.java)** - контроллер для экземпляров
  * **[IssueController](src/main/java/ru/otus/spring/homework/springproject/controllers/IssueController.java)** - контроллер для выдачи
  * **[IssueInstanceController](src/main/java/ru/otus/spring/homework/springproject/controllers/IssueInstanceController.java)** - контроллер для выдачи экземпляров
  * **[OrderController](src/main/java/ru/otus/spring/homework/springproject/controllers/OrderController.java)** - контроллер для заказа
  * **[RoleController](src/main/java/ru/otus/spring/homework/springproject/controllers/RoleController.java)** - контроллер для роли
  * **[UiController](src/main/java/ru/otus/spring/homework/springproject/controllers/GenreController.java)** - контроллер для поддержки UI
* Security
  * **[AuthRequest](src/main/java/ru/otus/spring/homework/springproject/security/AuthRequest.java)** - модель запроса аутентификации
  * **[AuthResponse](src/main/java/ru/otus/spring/homework/springproject/security/AuthResponse.java)** - модель ответа аутентификации    
  * **[CustomUserDetails](src/main/java/ru/otus/spring/homework/springproject/security/CustomUserDetails.java)** - модель пользователя
  * **[CustomUserDetailsService](src/main/java/ru/otus/spring/homework/springproject/security/CustomUserDetailsService.java)** - сервис пользователя
  * **[JwtFilter](src/main/java/ru/otus/spring/homework/springproject/security/JwtFilter.java)** - Jwt фильтр
  * **[JwtPayload](src/main/java/ru/otus/spring/homework/springproject/security/JwtPayload.java)** - paylod токена
  * **[JwtProvider](src/main/java/ru/otus/spring/homework/springproject/security/JwtProvider.java)** - провайдер jwt аутентификации
  * **[RegistrationRequest](src/main/java/ru/otus/spring/homework/springproject/security/RegistrationRequest.java)** - модель запроса на регистрацию пользователя
  * **[SecurityConfig](src/main/java/ru/otus/spring/homework/springproject/security/SecurityConfig.java)** - конфигурация безопасности

  
## Ресурсы

* **[db](src/main/resources/db)** - Flyway скрипты
* **[public](src/main/resources/public)** - web ресурсы, собранный проект Angular  
* **[application.yml](src/main/resources/application.yml)** - настройка приложения
* **[banner.txt](src/main/resources/banner.txt)** - баннер

## Сборка

`mvn clean package`

В результате сборки формируется два jar файла:

**./target/spring-project-0.0.1.jar** - содержит встроенные библиотеки, можно запускать
`java -Dfile.encoding=utf-8 -jar spring-project-0.0.1.jar`

`-Dfile.encoding=utf-8` - необходимо для корректного отображения русских букв в консоли, в Windows запускать рекомендуется в консоли `GitBash`. 
  
После запуска клиент Angular доступен по адресу `http://localhost:8080/`

## Запуск в режиме разработки

* в системе должен быть установлен NodeJs и Angular Cli `npm install -g @angular/cli`

1. `mvn clean package`
2. `java -Dfile.encoding=utf-8 -jar ./target/spring-project-0.0.1.jar`
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
