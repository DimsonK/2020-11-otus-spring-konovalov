CREATE SEQUENCE AUTHOR_SEQUENCE START WITH 10;
CREATE SEQUENCE BOOK_SEQUENCE START WITH 10;
CREATE SEQUENCE GENRE_SEQUENCE START WITH 10;
CREATE SEQUENCE COMMENT_SEQUENCE START WITH 20;

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES
(
    ID         BIGINT,
    GENRE_NAME VARCHAR(255),
    primary key (ID)
);

DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS
(
    ID          BIGINT,
    AUTHOR_NAME VARCHAR(255),
    primary key (ID)
);

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS
(
    ID        BIGINT,
    AUTHOR_ID BIGINT references AUTHORS (ID) on update cascade,
    BOOK_NAME VARCHAR(255),
    primary key (ID)
);

DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE COMMENTS
(
    ID      BIGINT,
    BOOK_ID BIGINT references BOOKS (ID) on delete cascade,
    POST_DATE VARCHAR(10),
    AUTHOR_NAME VARCHAR(64),
    CONTENT VARCHAR(400),
    primary key (ID)
);

DROP TABLE IF EXISTS BOOK_GENRES;
CREATE TABLE BOOK_GENRES
(
    BOOK_ID  BIGINT references BOOKS (ID) on delete cascade,
    GENRE_ID BIGINT references GENRES (ID),
    primary key (BOOK_ID, GENRE_ID)
);
