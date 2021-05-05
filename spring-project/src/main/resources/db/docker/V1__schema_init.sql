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

DROP SEQUENCE IF EXISTS INSTANCE_SEQUENCE;
CREATE SEQUENCE INSTANCE_SEQUENCE START WITH 10;

DROP SEQUENCE IF EXISTS ISSUE_SEQUENCE;
CREATE SEQUENCE ISSUE_SEQUENCE START WITH 10;

DROP SEQUENCE IF EXISTS BOOK_FILES_SEQUENCE;
CREATE SEQUENCE BOOK_FILES_SEQUENCE START WITH 10;

-- TABLES

-- authors
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

ALTER TABLE AUTHORS
ADD CONSTRAINT AUTHOR_NAME_UNIQUE
UNIQUE ( AUTHOR_NAME );

-- genres
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

ALTER TABLE GENRES
ADD CONSTRAINT GENRE_NAME_UNIQUE
UNIQUE ( GENRE_NAME );

-- books
DROP TABLE IF EXISTS BOOKS CASCADE;
CREATE TABLE BOOKS
(
    ID        BIGINT,
    BOOK_NAME VARCHAR(255),
    RARS INT,
    ACCESS_LEVEL INT,
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    AUTHOR_ID BIGINT references AUTHORS (ID) on update cascade,
    primary key (ID)
);

ALTER TABLE BOOKS
ADD CONSTRAINT BOOK_NAME_UNIQUE
UNIQUE ( BOOK_NAME );

-- comments
DROP TABLE IF EXISTS COMMENTS CASCADE;
CREATE TABLE COMMENTS
(
    ID      BIGINT,
    POST_DATE VARCHAR(10),
    AUTHOR_NAME VARCHAR(64),
    CONTENT VARCHAR(400),
    FAVORITE BOOLEAN,
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    BOOK_ID BIGINT references BOOKS (ID) on delete cascade,
    primary key (ID)
);

-- genres
DROP TABLE IF EXISTS BOOK_GENRES CASCADE;
CREATE TABLE BOOK_GENRES
(
    BOOK_ID  BIGINT references BOOKS (ID) on delete cascade,
    GENRE_ID BIGINT references GENRES (ID),
    primary key (BOOK_ID, GENRE_ID)
);

-- users
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

ALTER TABLE USERS
ADD CONSTRAINT USER_NAME_UNIQUE
UNIQUE ( USER_NAME );

-- roles
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

ALTER TABLE ROLES
ADD CONSTRAINT ROLE_NAME_UNIQUE
UNIQUE ( ROLE_NAME );

-- user_roles
DROP TABLE IF EXISTS USER_ROLES CASCADE;
CREATE TABLE USER_ROLES
(
    USER_ID  BIGINT references USERS (ID) on delete cascade,
    ROLE_ID BIGINT references ROLES (ID),
    primary key (USER_ID, ROLE_ID)
);

-- orders
DROP TABLE IF EXISTS ORDERS CASCADE;
CREATE TABLE ORDERS
(
    ID         BIGINT,
    ORDER_NUMBER VARCHAR(255),
    ORDER_TIME TIMESTAMP,
    STATUS VARCHAR(255),
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    USER_ID BIGINT references USERS (ID) on delete cascade,
    primary key (ID)
);


-- instances
DROP TABLE IF EXISTS INSTANCES CASCADE;
CREATE TABLE INSTANCES
(
    ID         BIGINT,
    INVENTORY_NUMBER VARCHAR(255),
    BOOK_ID    BIGINT references BOOKS (ID) on delete cascade,
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    primary key (ID)
);

ALTER TABLE INSTANCES
ADD CONSTRAINT INVENTORY_NUMBER_UNIQUE
UNIQUE ( INVENTORY_NUMBER );

-- issue
DROP TABLE IF EXISTS ISSUE CASCADE;
CREATE TABLE ISSUE
(
    ID         BIGINT,
    ISSUE_TIME TIMESTAMP,
    USER_ID    BIGINT references USERS (ID) on delete cascade,
    ORDER_ID   BIGINT references ORDERS (ID),
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    primary key (ID)
);

-- issue_instances
DROP TABLE IF EXISTS ISSUE_INSTANCES CASCADE;
CREATE TABLE ISSUE_INSTANCES
(
    ISSUE_ID  BIGINT references ISSUE (ID) on delete cascade,
    INSTANCE_ID BIGINT references INSTANCES (ID),
    STATUS VARCHAR(255),
    RETURN_TIME TIMESTAMP,
    primary key (ISSUE_ID, INSTANCE_ID)
);

-- book_orders
DROP TABLE IF EXISTS BOOK_ORDERS CASCADE;
CREATE TABLE BOOK_ORDERS
(
    BOOK_ID  BIGINT references BOOKS (ID) on delete cascade,
    ORDER_ID BIGINT references ORDERS (ID),
    primary key (BOOK_ID, ORDER_ID)
);

-- book_files
DROP TABLE IF EXISTS BOOK_FILES CASCADE;
CREATE TABLE BOOK_FILES
(
    ID         BIGINT,
    IS_ACTIVE  BOOLEAN,
    MD5    VARCHAR(255),
    FILE_PATH   VARCHAR(255),
    ORIGINAL_NAME   VARCHAR(255),
    FILENAME   VARCHAR(255),
    SIZE INT,
    BOOK_ID BIGINT references BOOKS (ID) on delete cascade,
    CREATED_AT TIMESTAMP,
    CREATED_BY VARCHAR(255),
    MODIFIED_AT TIMESTAMP,
    MODIFIED_BY VARCHAR(255),
    primary key (ID)
);

-- ADD DATA

-- GENRES
INSERT INTO GENRES
(id, genre_name, created_at, created_by, modified_at, modified_by)
VALUES(1, 'Detective', '2021-04-29 16:32:53.051', 'system_init', '2021-04-29 16:32:53.000', 'system_init');
INSERT INTO GENRES
(id, genre_name, created_at, created_by, modified_at, modified_by)
VALUES(2, 'History', '2021-04-29 16:32:53.000', 'system_init', '2021-04-29 16:32:53.000', 'system_init');
INSERT INTO GENRES
(id, genre_name, created_at, created_by, modified_at, modified_by)
VALUES(3, 'Fantasy', '2021-04-29 16:32:53.000', 'system_init', '2021-04-29 16:32:53.000', 'system_init');
INSERT INTO GENRES
(id, genre_name, created_at, created_by, modified_at, modified_by)
VALUES(4, 'Horror', '2021-04-29 16:32:53.000', 'system_init', '2021-04-29 16:32:53.000', 'system_init');
INSERT INTO GENRES
(id, genre_name, created_at, created_by, modified_at, modified_by)
VALUES(5, 'Technical', '2021-04-29 16:32:53.000', 'system_init', '2021-04-29 16:32:53.000', 'system_init');


-- AUTHORS
INSERT INTO AUTHORS
(id, author_name, created_at, created_by, modified_at, modified_by)
VALUES(1, 'Agatha Christie', '2021-04-29 16:28:00.920', 'system_init', '2021-04-29 16:28:15.901', 'system_init');
INSERT INTO AUTHORS
(id, author_name, created_at, created_by, modified_at, modified_by)
VALUES(2, 'Alexandre Dumas', '2021-04-29 16:28:03.348', 'system_init', '2021-04-29 16:28:17.362', 'system_init');
INSERT INTO AUTHORS
(id, author_name, created_at, created_by, modified_at, modified_by)
VALUES(3, 'Jules Gabriel Verne', '2021-04-29 16:28:04.663', 'system_init', '2021-04-29 16:28:18.251', 'system_init');
INSERT INTO AUTHORS
(id, author_name, created_at, created_by, modified_at, modified_by)
VALUES(4, 'Edgar Allan Poe', '2021-04-29 16:28:05.713', 'system_init', '2021-04-29 16:28:19.116', 'system_init');
INSERT INTO AUTHORS
(id, author_name, created_at, created_by, modified_at, modified_by)
VALUES(5, 'Stephen Edwin King', '2021-04-29 16:28:06.496', 'system_init', '2021-04-29 16:28:19.941', 'system_init');
INSERT INTO AUTHORS
(id, author_name, created_at, created_by, modified_at, modified_by)
VALUES(6, 'Elizabeth McNeill', '2021-04-29 16:28:07.205', 'system_init', '2021-04-29 16:28:20.762', 'system_init');
INSERT INTO AUTHORS
(id, author_name, created_at, created_by, modified_at, modified_by)
VALUES(7, 'E L James, Zachary Webber, et al.', '2021-04-29 16:28:07.854', 'system_init', '2021-04-29 16:28:21.573', 'system_init');
INSERT INTO AUTHORS
(id, author_name, created_at, created_by, modified_at, modified_by)
VALUES(8, 'Попов, Шойгу и Ко', '2021-04-29 16:28:08.492', 'system_init', '2021-04-29 16:28:22.428', 'system_init');
INSERT INTO AUTHORS
(id, author_name, created_at, created_by, modified_at, modified_by)
VALUES(9, 'Уралвагонзавод и сотоварищи', '2021-04-29 16:28:09.154', 'system_init', '2021-04-29 16:28:23.269', 'system_init');

-- BOOKS
INSERT INTO BOOKS
(id, author_id, book_name, rars, access_level, created_at, created_by, modified_at, modified_by)
VALUES(1, 1, 'Murder on the Orient Express', 12, 2, '2021-04-29 16:30:29.571', 'system_init', '2021-04-29 16:30:53.279', 'system_init');
INSERT INTO BOOKS
(id, author_id, book_name, rars, access_level, created_at, created_by, modified_at, modified_by)
VALUES(2, 2, 'The Three Musketeers', 6, 2, '2021-04-29 16:30:29.000', 'system_init', '2021-04-29 16:30:53.000', 'system_init');
INSERT INTO BOOKS
(id, author_id, book_name, rars, access_level, created_at, created_by, modified_at, modified_by)
VALUES(3, 3, 'Twenty Thousand Leagues Under the Sea', 6, 2, '2021-04-29 16:30:29.000', 'system_init', '2021-04-29 16:30:53.000', 'system_init');
INSERT INTO BOOKS
(id, author_id, book_name, rars, access_level, created_at, created_by, modified_at, modified_by)
VALUES(4, 4, 'The Gold Bug', 6, 2, '2021-04-29 16:30:29.000', 'system_init', '2021-04-29 16:30:53.000', 'system_init');
INSERT INTO BOOKS
(id, author_id, book_name, rars, access_level, created_at, created_by, modified_at, modified_by)
VALUES(5, 5, 'It', 16, 2, '2021-04-29 16:30:29.000', 'system_init', '2021-04-29 16:30:53.000', 'system_init');
INSERT INTO BOOKS
(id, author_id, book_name, rars, access_level, created_at, created_by, modified_at, modified_by)
VALUES(6, 6, 'Nine and a Half Weeks: A Memoir of a Love Affair', 18, 2, '2021-04-29 16:30:29.000', 'system_init', '2021-04-29 16:30:53.000', 'system_init');
INSERT INTO BOOKS
(id, author_id, book_name, rars, access_level, created_at, created_by, modified_at, modified_by)
VALUES(7, 7, 'Fifty Shades of Grey', 18, 2, '2021-04-29 16:30:29.000', 'system_init', '2021-04-29 16:30:53.000', 'system_init');
INSERT INTO BOOKS
(id, author_id, book_name, rars, access_level, created_at, created_by, modified_at, modified_by)
VALUES(8, 8, 'Радио не для всех. Закрытые частоты Минобороны.', 18, 1, '2021-04-29 16:30:29.000', 'system_init', '2021-04-29 16:30:53.000', 'system_init');
INSERT INTO BOOKS
(id, author_id, book_name, rars, access_level, created_at, created_by, modified_at, modified_by)
VALUES(9, 9, 'Оценка проходимости танка Армата в плотных слоях атмосферы', 18, 1, '2021-04-29 16:30:29.000', 'system_init', '2021-04-29 16:30:53.000', 'system_init');

-- BOOK_GENRES
INSERT INTO BOOK_GENRES
(book_id, genre_id)
VALUES(1, 1);
INSERT INTO BOOK_GENRES
(book_id, genre_id)
VALUES(2, 2);
INSERT INTO BOOK_GENRES
(book_id, genre_id)
VALUES(3, 3);
INSERT INTO BOOK_GENRES
(book_id, genre_id)
VALUES(4, 3);
INSERT INTO BOOK_GENRES
(book_id, genre_id)
VALUES(5, 4);
INSERT INTO BOOK_GENRES
(book_id, genre_id)
VALUES(6, 2);
INSERT INTO BOOK_GENRES
(book_id, genre_id)
VALUES(7, 2);
INSERT INTO BOOK_GENRES
(book_id, genre_id)
VALUES(8, 5);
INSERT INTO BOOK_GENRES
(book_id, genre_id)
VALUES(9, 5);

-- COMMENTS
INSERT INTO COMMENTS
(id, book_id, post_date, author_name, content, favorite, created_at, created_by, modified_at, modified_by)
VALUES(1, 1, '10.10.2020', 'Вася', 'Замечательная классическая книга! Написано, как и все остальные рассказы Агаты, очень интересным и доступным языком.', true, '2021-04-29 16:31:48.745', 'system_init', '2021-04-29 16:31:48.000', 'system_init');
INSERT INTO COMMENTS
(id, book_id, post_date, author_name, content, favorite, created_at, created_by, modified_at, modified_by)
VALUES(2, 1, '25.09.2019', 'Петя', 'чтение на одном дыхании! браво Агата Кристи! развязка удивляет, на протяжении чтения сам ломаешь голову и чувствуешь себя детективом! к прочтению рекомендую!!!', false, '2021-04-29 16:31:48.000', 'system_init', '2021-04-29 16:31:48.000', 'system_init');
INSERT INTO COMMENTS
(id, book_id, post_date, author_name, content, favorite, created_at, created_by, modified_at, modified_by)
VALUES(3, 2, '01.05.2018', 'Мария Ивановна', '"Три мушкетёра" Александра Дюма – любимая книга моего детства. Но её можно читать в любом возрасте.', true, '2021-04-29 16:31:48.000', 'system_init', '2021-04-29 16:31:48.000', 'system_init');
INSERT INTO COMMENTS
(id, book_id, post_date, author_name, content, favorite, created_at, created_by, modified_at, modified_by)
VALUES(4, 2, '21.06.2017', 'SubZerro', 'Великолепная классика в отличном переводе! Книга из детства.', false, '2021-04-29 16:31:48.000', 'system_init', '2021-04-29 16:31:48.000', 'system_init');
INSERT INTO COMMENTS
(id, book_id, post_date, author_name, content, favorite, created_at, created_by, modified_at, modified_by)
VALUES(5, 3, '08.09.2019', 'Викинг', 'Прочла почти все произведение Жюля Верна. Обожаю его манеру написания, развитие сюжетной линии и такие колоритные и интересные герои книг.', true, '2021-04-29 16:31:48.000', 'system_init', '2021-04-29 16:31:48.000', 'system_init');
INSERT INTO COMMENTS
(id, book_id, post_date, author_name, content, favorite, created_at, created_by, modified_at, modified_by)
VALUES(6, 3, '24.05.2020', 'acidMaster', 'Прекрасное произведение, способное подарить увлекательное приключение, не выходя из дома как ребёнку, так и взрослому.', false, '2021-04-29 16:31:48.000', 'system_init', '2021-04-29 16:31:48.000', 'system_init');
INSERT INTO COMMENTS
(id, book_id, post_date, author_name, content, favorite, created_at, created_by, modified_at, modified_by)
VALUES(7, 4, '06.11.2018', 'бабаМиша', 'Очень захватывающий сюжет, особенно, когда одержимые загадкой золотого жука молодые люди тщательно изучают шифр.', true, '2021-04-29 16:31:48.000', 'system_init', '2021-04-29 16:31:48.000', 'system_init');
INSERT INTO COMMENTS
(id, book_id, post_date, author_name, content, favorite, created_at, created_by, modified_at, modified_by)
VALUES(8, 4, '05.12.2019', 'Jakob Jenkov', 'Нестандартно мыслящий человек может вызывать у других смешанные чувства, а порой казаться сумасшедшим.', false, '2021-04-29 16:31:48.000', 'system_init', '2021-04-29 16:31:48.000', 'system_init');
INSERT INTO COMMENTS
(id, book_id, post_date, author_name, content, favorite, created_at, created_by, modified_at, modified_by)
VALUES(9, 5, '06.05.2017', 'SubZero', 'Впервые прочитал книгу в 14-15 лет, и признаюсь во время чтения испытывал «живой» страх, как будто всё описанное происходило со мной....очень впечатляет, настоящий хоррор от Мастера жанра!', true, '2021-04-29 16:31:48.000', 'system_init', '2021-04-29 16:31:48.000', 'system_init');
INSERT INTO COMMENTS
(id, book_id, post_date, author_name, content, favorite, created_at, created_by, modified_at, modified_by)
VALUES(10, 5, '24.02.2019', 'Читатель', 'Это сногсшибательная книга, читается на одном дыхании. Она вытаскивает наши детские страхи из подсознания. Манера написания заставляет пережить события книги вместе с героями', false, '2021-04-29 16:31:48.000', 'system_init', '2021-04-29 16:31:48.000', 'system_init');
INSERT INTO COMMENTS
(id, book_id, post_date, author_name, content, favorite, created_at, created_by, modified_at, modified_by)
VALUES(11, 6, '06.06.2019', 'Rafe Bennett', 'Not as good as the film', true, '2021-04-29 16:31:48.000', 'system_init', '2021-04-29 16:31:48.000', 'system_init');
INSERT INTO COMMENTS
(id, book_id, post_date, author_name, content, favorite, created_at, created_by, modified_at, modified_by)
VALUES(12, 6, '12.08.2019', 'Mr A P Beattie', 'Excellent, engrossing the original 50 shades of grey', true, '2021-04-29 16:31:48.000', 'system_init', '2021-04-29 16:31:48.000', 'system_init');

-- USERS
INSERT INTO USERS
(id, user_name, user_password, first_name, last_name, age, access_level, created_at, created_by, modified_at, modified_by)
VALUES(1, 'admin', '$2a$10$76Gp22UH/LqZUV2b.zKfYOe51GNEK6J2NmXL7m20faFFMOqW0ENZa', 'Иван', 'Иванов', 35, 0, '2021-04-29 16:35:12.036', 'system_init', '2021-04-29 16:35:12.000', 'system_init');
INSERT INTO USERS
(id, user_name, user_password, first_name, last_name, age, access_level, created_at, created_by, modified_at, modified_by)
VALUES(2, 'user1', '$2a$10$76Gp22UH/LqZUV2b.zKfYOe51GNEK6J2NmXL7m20faFFMOqW0ENZa', 'Петр', 'Петров', 12, 2, '2021-04-29 16:35:12.000', 'system_init', '2021-04-29 16:35:12.000', 'system_init');
INSERT INTO USERS
(id, user_name, user_password, first_name, last_name, age, access_level, created_at, created_by, modified_at, modified_by)
VALUES(3, 'user2', '$2a$10$76Gp22UH/LqZUV2b.zKfYOe51GNEK6J2NmXL7m20faFFMOqW0ENZa', 'Николай', 'Сидоров', 45, 1, '2021-04-29 16:35:12.000', 'system_init', '2021-04-29 16:35:12.000', 'system_init');
INSERT INTO USERS
(id, user_name, user_password, first_name, last_name, age, access_level, created_at, created_by, modified_at, modified_by)
VALUES(4, 'user3', '$2a$10$76Gp22UH/LqZUV2b.zKfYOe51GNEK6J2NmXL7m20faFFMOqW0ENZa', 'Ермолай', 'Сидоров', 10, 1, '2021-04-29 16:35:12.000', 'system_init', '2021-04-29 16:35:12.000', 'system_init');

-- ROLES
INSERT INTO ROLES
(id, role_name, created_at, created_by, modified_at, modified_by)
VALUES(1, 'ADMIN', '2021-04-29 16:34:01.849', 'system_init', '2021-04-29 16:34:01.000', 'system_init');
INSERT INTO ROLES
(id, role_name, created_at, created_by, modified_at, modified_by)
VALUES(2, 'USER', '2021-04-29 16:34:01.000', 'system_init', '2021-04-29 16:34:01.000', 'system_init');
INSERT INTO ROLES
(id, role_name, created_at, created_by, modified_at, modified_by)
VALUES(3, 'ANONYMOUS', '2021-04-29 16:34:01.000', 'system_init', '2021-04-29 16:34:01.000', 'system_init');

-- USER_ROLES
INSERT INTO USER_ROLES
(user_id, role_id)
VALUES(1, 1);
INSERT INTO USER_ROLES
(user_id, role_id)
VALUES(2, 2);
INSERT INTO USER_ROLES
(user_id, role_id)
VALUES(3, 2);
INSERT INTO USER_ROLES
(user_id, role_id)
VALUES(4, 2);

-- ORDERS
INSERT INTO ORDERS
(id, order_number, created_at, created_by, modified_at, modified_by, order_time, status, user_id)
VALUES(1, 'I100', '2021-04-29 16:34:01.000', 'system_init', '2021-04-29 16:34:01.000', 'system_init', '2021-05-01 12:00:00.000', 'OPEN', 1);

INSERT INTO BOOK_ORDERS
(order_id, book_id)
VALUES(1, 1);
INSERT INTO BOOK_ORDERS
(order_id, book_id)
VALUES(1, 2);
INSERT INTO BOOK_ORDERS
(order_id, book_id)
VALUES(1, 3);
