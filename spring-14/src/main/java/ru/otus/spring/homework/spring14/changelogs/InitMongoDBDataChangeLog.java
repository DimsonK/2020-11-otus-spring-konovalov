package ru.otus.spring.homework.spring14.changelogs;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import io.changock.migration.api.annotations.ChangeLog;
import io.changock.migration.api.annotations.ChangeSet;
import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.homework.spring14.model.mongo.Author;
import ru.otus.spring.homework.spring14.model.mongo.Book;
import ru.otus.spring.homework.spring14.model.mongo.Comment;
import ru.otus.spring.homework.spring14.model.mongo.Genre;

@Slf4j
@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private Genre genreDetective;
    private Genre genreHistory;
    private Genre genreFantasy;
    private Genre genreHorror;
    private Author author1;
    private Author author2;
    private Author author3;
    private Author author4;
    private Author author5;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;

    @ChangeSet(order = "000", id = "dropDB", author = "admin", runAlways = true)
    public void dropDB(MongoDatabase database) {
        log.info("Drop database {}", database.getName());
        database.drop();
    }

    @ChangeSet(order = "001", id = "initGenres", author = "admin", runAlways = true)
    public void initGenres(MongockTemplate repository) {
        genreDetective = repository.save(new Genre("Detective"));
        genreHistory = repository.save(new Genre("History"));
        genreFantasy = repository.save(new Genre("Fantasy"));
        genreHorror = repository.save(new Genre("Horror"));
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "admin", runAlways = true)
    public void initAuthors(MongockTemplate repository) {
        author1 = repository.save(new Author("Agatha Christie"));
        author2 = repository.save(new Author("Alexandre Dumas"));
        author3 = repository.save(new Author("Jules Gabriel Verne"));
        author4 = repository.save(new Author("Edgar Allan Poe"));
        author5 = repository.save(new Author("Stephen Edwin King"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "admin", runAlways = true)
    public void initBooks(MongockTemplate repository) {
        book1 = repository.save(new Book("Murder on the Orient Express", author1, genreDetective));
        book2 = repository.save(new Book("The Three Musketeers", author2, genreHistory));
        book3 = repository.save(new Book("Twenty Thousand Leagues Under the Sea", author3, genreFantasy));
        book4 = repository.save(new Book("The Gold Bug", author4, genreFantasy));
        book5 = repository.save(new Book("It", author5, genreHorror));
    }

    @ChangeSet(order = "004", id = "initComments", author = "admin", runAlways = true)
    public void initComments(MongockTemplate repository) {
        repository.save(new Comment("10.10.2020", "Вася",
                "Замечательная классическая книга! Написано, как и все остальные рассказы Агаты, очень интересным и доступным языком.", true, book1));
        repository.save(new Comment("25.09.2019", "Петя",
                "чтение на одном дыхании! браво Агата Кристи! развязка удивляет, на протяжении чтения сам ломаешь голову и чувствуешь себя детективом! к прочтению рекомендую!!!",
                false, book1));
        repository.save(new Comment("01.05.2018", "Мария Ивановна",
                "'Три мушкетёра' Александра Дюма – любимая книга моего детства. Но её можно читать в любом возрасте.", true, book2));
        repository.save(new Comment("21.06.2017", "SubZerro", "Великолепная классика в отличном переводе! Книга из детства.", false, book2));
        repository.save(new Comment("08.09.2019", "Викинг",
                "Прочла почти все произведение Жюля Верна. Обожаю его манеру написания, развитие сюжетной линии и такие колоритные и интересные герои книг.",
                false, book3));
        repository.save(new Comment("24.05.2020", "acidMaster",
                "Прекрасное произведение, способное подарить увлекательное приключение, не выходя из дома как ребёнку, так и взрослому.", true, book3));
        repository.save(new Comment("06.11.2018", "бабаМиша",
                "Очень захватывающий сюжет, особенно, когда одержимые загадкой золотого жука молодые люди тщательно изучают шифр.", false, book4));
        repository.save(new Comment("05.12.2019", "Jakob Jenkov",
                "Нестандартно мыслящий человек может вызывать у других смешанные чувства, а порой казаться сумасшедшим.", true, book4));
        repository.save(new Comment("06.05.2017", "SubZero",
                "Впервые прочитал книгу в 14-15 лет, и признаюсь во время чтения испытывал «живой» страх, как будто всё описанное происходило со мной....очень впечатляет, настоящий хоррор от Мастера жанра!",
                true, book5));
        repository.save(new Comment("24.02.2019", "Читатель",
                "Это сногсшибательная книга, читается на одном дыхании. Она вытаскивает наши детские страхи из подсознания. Манера написания заставляет пережить события книги вместе с героями",
                false, book5));

    }

}
