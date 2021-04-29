DROP SEQUENCE IF EXISTS AUTHOR_SEQUENCE;
CREATE SEQUENCE AUTHOR_SEQUENCE START WITH 10;

DROP SEQUENCE IF EXISTS BOOK_SEQUENCE;
CREATE SEQUENCE BOOK_SEQUENCE START WITH 10;

DROP SEQUENCE IF EXISTS GENRE_SEQUENCE;
CREATE SEQUENCE GENRE_SEQUENCE START WITH 10;

DROP SEQUENCE IF EXISTS COMMENT_SEQUENCE;
CREATE SEQUENCE COMMENT_SEQUENCE START WITH 20;

DROP SEQUENCE IF EXISTS USER_SEQUENCE;
CREATE SEQUENCE USER_SEQUENCE START WITH 10;

DROP SEQUENCE IF EXISTS ROLE_SEQUENCE;
CREATE SEQUENCE ROLE_SEQUENCE START WITH 10;

DROP SEQUENCE IF EXISTS ORDER_SEQUENCE;
CREATE SEQUENCE ORDER_SEQUENCE START WITH 10;

DROP TABLE IF EXISTS GENRES CASCADE;
CREATE TABLE GENRES
(
    ID         BIGINT,
    GENRE_NAME VARCHAR(255),
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    primary key (ID)
);

DROP TABLE IF EXISTS AUTHORS CASCADE;
CREATE TABLE AUTHORS
(
    ID          BIGINT,
    AUTHOR_NAME VARCHAR(255),
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    primary key (ID)
);

DROP TABLE IF EXISTS BOOKS CASCADE;
CREATE TABLE BOOKS
(
    ID        BIGINT,
    AUTHOR_ID BIGINT references AUTHORS (ID) on update cascade,
    BOOK_NAME VARCHAR(255),
    RARS INT,
    ACCESS_LEVEL INT,
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    primary key (ID)
);

DROP TABLE IF EXISTS COMMENTS CASCADE;
CREATE TABLE COMMENTS
(
    ID      BIGINT,
    BOOK_ID BIGINT references BOOKS (ID) on delete cascade,
    POST_DATE VARCHAR(10),
    AUTHOR_NAME VARCHAR(64),
    CONTENT VARCHAR(400),
    FAVORITE BOOLEAN,
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    primary key (ID)
);

DROP TABLE IF EXISTS BOOK_GENRES CASCADE;
CREATE TABLE BOOK_GENRES
(
    BOOK_ID  BIGINT references BOOKS (ID) on delete cascade,
    GENRE_ID BIGINT references GENRES (ID),
    primary key (BOOK_ID, GENRE_ID)
);

DROP TABLE IF EXISTS USERS CASCADE;
CREATE TABLE USERS
(
    ID         BIGINT,
    USER_NAME VARCHAR(255),
    USER_PASSWORD VARCHAR(255),
    FIRST_NAME VARCHAR(255),
    LAST_NAME VARCHAR(255),
    AGE INT,
    ACCESS_LEVEL INT,
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    primary key (ID)
);

DROP TABLE IF EXISTS ROLES CASCADE;
CREATE TABLE ROLES
(
    ID         BIGINT,
    ROLE_NAME VARCHAR(255),
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    primary key (ID)
);

DROP TABLE IF EXISTS USER_ROLES CASCADE;
CREATE TABLE USER_ROLES
(
    USER_ID  BIGINT references USERS (ID) on delete cascade,
    ROLE_ID BIGINT references ROLES (ID),
    primary key (USER_ID, ROLE_ID)
);

DROP TABLE IF EXISTS ORDERS CASCADE;
CREATE TABLE ORDERS
(
    ID         BIGINT,
    ORDERTIME VARCHAR(255),
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    primary key (ID)
);


-- GENRES
insert into GENRES (ID, GENRE_NAME) values (1, 'Detective');
insert into GENRES (ID, GENRE_NAME) values (2, 'History');
insert into GENRES (ID, GENRE_NAME) values (3, 'Fantasy');
insert into GENRES (ID, GENRE_NAME) values (4, 'Horror');

-- AUTHORS
insert into AUTHORS (ID, AUTHOR_NAME) values (1, 'Agatha Christie');
insert into AUTHORS (ID, AUTHOR_NAME) values (2, 'Alexandre Dumas');
insert into AUTHORS (ID, AUTHOR_NAME) values (3, 'Jules Gabriel Verne');
insert into AUTHORS (ID, AUTHOR_NAME) values (4, 'Edgar Allan Poe');
insert into AUTHORS (ID, AUTHOR_NAME) values (5, 'Stephen Edwin King');

-- BOOKS
insert into BOOKS (ID, AUTHOR_ID, BOOK_NAME, RARS, ACCESS_LEVEL) values (1, 1, 'Murder on the Orient Express', 12, 2);
insert into BOOKS (ID, AUTHOR_ID, BOOK_NAME, RARS, ACCESS_LEVEL) values (2, 2, 'The Three Musketeers', 6, 2);
insert into BOOKS (ID, AUTHOR_ID, BOOK_NAME, RARS, ACCESS_LEVEL) values (3, 3, 'Twenty Thousand Leagues Under the Sea', 6, 2);
insert into BOOKS (ID, AUTHOR_ID, BOOK_NAME, RARS, ACCESS_LEVEL) values (4, 4, 'The Gold Bug', 6, 2);
insert into BOOKS (ID, AUTHOR_ID, BOOK_NAME, RARS, ACCESS_LEVEL) values (5, 5, 'It', 16, 2);

-- BOOK_GENRES
insert into BOOK_GENRES (BOOK_ID, GENRE_ID) values (1, 1);
insert into BOOK_GENRES (BOOK_ID, GENRE_ID) values (2, 2);
insert into BOOK_GENRES (BOOK_ID, GENRE_ID) values (3, 3);
insert into BOOK_GENRES (BOOK_ID, GENRE_ID) values (4, 3);
insert into BOOK_GENRES (BOOK_ID, GENRE_ID) values (5, 4);

-- COMMENTS
insert into COMMENTS (ID, BOOK_ID, POST_DATE, AUTHOR_NAME, CONTENT, FAVORITE) values ( 1,  1, '10.10.2020', 'Вася', 'Замечательная классическая книга! Написано, как и все остальные рассказы Агаты, очень интересным и доступным языком.', true);
insert into COMMENTS (ID, BOOK_ID, POST_DATE, AUTHOR_NAME, CONTENT, FAVORITE) values ( 2,  1, '25.09.2019', 'Петя', 'чтение на одном дыхании! браво Агата Кристи! развязка удивляет, на протяжении чтения сам ломаешь голову и чувствуешь себя детективом! к прочтению рекомендую!!!', false);
insert into COMMENTS (ID, BOOK_ID, POST_DATE, AUTHOR_NAME, CONTENT, FAVORITE) values ( 3,  2, '01.05.2018', 'Мария Ивановна', '"Три мушкетёра" Александра Дюма – любимая книга моего детства. Но её можно читать в любом возрасте.', true);
insert into COMMENTS (ID, BOOK_ID, POST_DATE, AUTHOR_NAME, CONTENT, FAVORITE) values ( 4,  2, '21.06.2017', 'SubZerro', 'Великолепная классика в отличном переводе! Книга из детства.', false);
insert into COMMENTS (ID, BOOK_ID, POST_DATE, AUTHOR_NAME, CONTENT, FAVORITE) values ( 5,  3, '08.09.2019', 'Викинг', 'Прочла почти все произведение Жюля Верна. Обожаю его манеру написания, развитие сюжетной линии и такие колоритные и интересные герои книг.', true);
insert into COMMENTS (ID, BOOK_ID, POST_DATE, AUTHOR_NAME, CONTENT, FAVORITE) values ( 6,  3, '24.05.2020', 'acidMaster', 'Прекрасное произведение, способное подарить увлекательное приключение, не выходя из дома как ребёнку, так и взрослому.', false);
insert into COMMENTS (ID, BOOK_ID, POST_DATE, AUTHOR_NAME, CONTENT, FAVORITE) values ( 7,  4, '06.11.2018', 'бабаМиша', 'Очень захватывающий сюжет, особенно, когда одержимые загадкой золотого жука молодые люди тщательно изучают шифр.', true);
insert into COMMENTS (ID, BOOK_ID, POST_DATE, AUTHOR_NAME, CONTENT, FAVORITE) values ( 8,  4, '05.12.2019', 'Jakob Jenkov', 'Нестандартно мыслящий человек может вызывать у других смешанные чувства, а порой казаться сумасшедшим.', false);
insert into COMMENTS (ID, BOOK_ID, POST_DATE, AUTHOR_NAME, CONTENT, FAVORITE) values ( 9,  5, '06.05.2017', 'SubZero', 'Впервые прочитал книгу в 14-15 лет, и признаюсь во время чтения испытывал «живой» страх, как будто всё описанное происходило со мной....очень впечатляет, настоящий хоррор от Мастера жанра!', true);
insert into COMMENTS (ID, BOOK_ID, POST_DATE, AUTHOR_NAME, CONTENT, FAVORITE) values ( 10,  5, '24.02.2019', 'Читатель', 'Это сногсшибательная книга, читается на одном дыхании. Она вытаскивает наши детские страхи из подсознания. Манера написания заставляет пережить события книги вместе с героями', false);

-- USERS
insert into USERS (ID, USER_NAME, USER_PASSWORD, FIRST_NAME, LAST_NAME, AGE, ACCESS_LEVEL) values (1, 'admin', '$2a$10$76Gp22UH/LqZUV2b.zKfYOe51GNEK6J2NmXL7m20faFFMOqW0ENZa', 'Иван', 'Иванов', 35, 0);
insert into USERS (ID, USER_NAME, USER_PASSWORD, FIRST_NAME, LAST_NAME, AGE, ACCESS_LEVEL) values (2, 'user1', '$2a$10$76Gp22UH/LqZUV2b.zKfYOe51GNEK6J2NmXL7m20faFFMOqW0ENZa', 'Петр', 'Петров', 12, 2);
insert into USERS (ID, USER_NAME, USER_PASSWORD, FIRST_NAME, LAST_NAME, AGE, ACCESS_LEVEL) values (3, 'user2', '$2a$10$76Gp22UH/LqZUV2b.zKfYOe51GNEK6J2NmXL7m20faFFMOqW0ENZa', 'Николай', 'Сидоров', 45, 1);
insert into USERS (ID, USER_NAME, USER_PASSWORD, FIRST_NAME, LAST_NAME, AGE, ACCESS_LEVEL) values (4, 'user3', '$2a$10$76Gp22UH/LqZUV2b.zKfYOe51GNEK6J2NmXL7m20faFFMOqW0ENZa', 'Ермолай', 'Сидоров', 10, 1);

-- ROLES
insert into ROLES (ID, ROLE_NAME) values (1, 'ADMIN');
insert into ROLES (ID, ROLE_NAME) values (2, 'USER');
insert into ROLES (ID, ROLE_NAME) values (3, 'ANONYMOUS');

-- USER_ROLES
insert into USER_ROLES (USER_ID, ROLE_ID) values (1, 1);
insert into USER_ROLES (USER_ID, ROLE_ID) values (2, 2);
insert into USER_ROLES (USER_ID, ROLE_ID) values (3, 2);
insert into USER_ROLES (USER_ID, ROLE_ID) values (4, 2);
