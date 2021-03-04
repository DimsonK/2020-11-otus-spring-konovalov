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
insert into BOOKS (ID, AUTHOR_ID, BOOK_NAME) values (1, 1, 'Murder on the Orient Express');
insert into BOOKS (ID, AUTHOR_ID, BOOK_NAME) values (2, 2, 'The Three Musketeers');
insert into BOOKS (ID, AUTHOR_ID, BOOK_NAME) values (3, 3, 'Twenty Thousand Leagues Under the Sea');
insert into BOOKS (ID, AUTHOR_ID, BOOK_NAME) values (4, 4, 'The Gold Bug');
insert into BOOKS (ID, AUTHOR_ID, BOOK_NAME) values (5, 5, 'It');

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
insert into USERS (ID, USER_NAME, USER_PASSWORD, FIRST_NAME, LAST_NAME) values (1, 'admin', '$2a$10$76Gp22UH/LqZUV2b.zKfYOe51GNEK6J2NmXL7m20faFFMOqW0ENZa', 'Иван', 'Иванов');
insert into USERS (ID, USER_NAME, USER_PASSWORD, FIRST_NAME, LAST_NAME) values (2, 'user', '$2a$10$76Gp22UH/LqZUV2b.zKfYOe51GNEK6J2NmXL7m20faFFMOqW0ENZa', 'Петр', 'Петров');
