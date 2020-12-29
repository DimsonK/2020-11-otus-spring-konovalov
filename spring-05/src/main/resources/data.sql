insert into GENRES (ID, GENRE_NAME) values (1, 'Detective');
insert into GENRES (ID, GENRE_NAME) values (2, 'History');
insert into GENRES (ID, GENRE_NAME) values (3, 'Fantasy');
insert into GENRES (ID, GENRE_NAME) values (4, 'Horror');

insert into AUTHORS (ID, AUTHOR_NAME) values (1, 'Agatha Christie');
insert into AUTHORS (ID, AUTHOR_NAME) values (2, 'Alexandre Dumas');
insert into AUTHORS (ID, AUTHOR_NAME) values (3, 'Jules Gabriel Verne');
insert into AUTHORS (ID, AUTHOR_NAME) values (4, 'Edgar Allan Poe');
insert into AUTHORS (ID, AUTHOR_NAME) values (5, 'Stephen Edwin King');

insert into BOOKS (ID, GENRE_ID, AUTHOR_ID, BOOK_NAME) values (1, 1, 1, 'Murder on the Orient Express');
insert into BOOKS (ID, GENRE_ID, AUTHOR_ID, BOOK_NAME) values (2, 2, 2, 'The Three Musketeers');
insert into BOOKS (ID, GENRE_ID, AUTHOR_ID, BOOK_NAME) values (3, 3, 3, 'Twenty Thousand Leagues Under the Sea');
insert into BOOKS (ID, GENRE_ID, AUTHOR_ID, BOOK_NAME) values (4, 3, 4, 'The Gold Bug');
insert into BOOKS (ID, GENRE_ID, AUTHOR_ID, BOOK_NAME) values (5, 4, 5, 'It');
