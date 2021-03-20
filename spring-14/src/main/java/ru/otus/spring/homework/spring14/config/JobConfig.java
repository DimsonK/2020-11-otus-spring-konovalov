package ru.otus.spring.homework.spring14.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.homework.spring14.model.h2.*;
import ru.otus.spring.homework.spring14.model.mongo.Author;
import ru.otus.spring.homework.spring14.model.mongo.Book;
import ru.otus.spring.homework.spring14.model.mongo.Comment;
import ru.otus.spring.homework.spring14.model.mongo.Genre;
import ru.otus.spring.homework.spring14.service.SequenceService;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Configuration
public class JobConfig {
    private static final int CHUNK_SIZE = 5;

    public static final String IMPORT_JOB_NAME = "importJob";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MongoTemplate mongoTemplate;
    private final DataSource dataSource;
    private final SequenceService sequenceService;

    public JobConfig(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            MongoTemplate mongoTemplate,
            DataSource dataSource,
            SequenceService sequenceService
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.mongoTemplate = mongoTemplate;
        this.dataSource = dataSource;
        this.sequenceService = sequenceService;
    }

    /* READERS */
    @StepScope
    @Bean
    public MongoItemReader<Genre> genreMongoItemReader() {
        var reader = new MongoItemReader<Genre>();
        reader.setTemplate(mongoTemplate);
        reader.setQuery("{}");
        reader.setSort(Map.of("name", Direction.ASC));
        reader.setTargetType(Genre.class);
        return reader;
    }

    @StepScope
    @Bean
    public MongoItemReader<Author> authorMongoItemReader() {
        var reader = new MongoItemReader<Author>();
        reader.setTemplate(mongoTemplate);
        reader.setQuery("{}");
        reader.setSort(Map.of("name", Direction.ASC));
        reader.setTargetType(Author.class);
        return reader;
    }

    @StepScope
    @Bean
    public MongoItemReader<Book> bookMongoItemReader() {
        var reader = new MongoItemReader<Book>();
        reader.setTemplate(mongoTemplate);
        reader.setQuery("{}");
        reader.setSort(Map.of("name", Direction.ASC));
        reader.setTargetType(Book.class);
        return reader;
    }

    @StepScope
    @Bean
    public MongoItemReader<Comment> commentMongoItemReader() {
        var reader = new MongoItemReader<Comment>();
        reader.setTemplate(mongoTemplate);
        reader.setQuery("{}");
        reader.setSort(Map.of("authorName", Direction.ASC));
        reader.setTargetType(Comment.class);
        return reader;
    }

    @StepScope
    @Bean
    public ItemReader<BookGenresDao> bookGenresDaoItemReader() {
        return new ListItemReader<>(sequenceService.getBookGenresDaoList());
    }

    /* Processors */
    @StepScope
    @Bean
    public ItemProcessor<Genre, GenreDao> genreSequenceProcessor(SequenceService service) {
        return service::updateGenreId;
    }

    @StepScope
    @Bean
    public ItemProcessor<Author, AuthorDao> authorSequenceProcessor(SequenceService service) {
        return service::updateAuthorId;
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, BookDao> bookSequenceProcessor(SequenceService service) {
        return service::updateBookId;
    }

    @StepScope
    @Bean
    public ItemProcessor<Comment, CommentDao> commentSequenceProcessor(SequenceService service) {
        return service::updateCommentId;
    }

    /* Writers */
    @StepScope
    @Bean
    public ItemWriter<GenreDao> genreWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<GenreDao>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into GENRES (ID, GENRE_NAME) values (:id, :name)")
                .dataSource(dataSource)
                .build();
    }

    @StepScope
    @Bean
    public JdbcBatchItemWriter<AuthorDao> authorWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<AuthorDao>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into AUTHORS (ID, AUTHOR_NAME) values (:id, :name)")
                .dataSource(dataSource)
                .build();
    }

    @StepScope
    @Bean
    public JdbcBatchItemWriter<BookDao> bookWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<BookDao>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into BOOKS (ID, AUTHOR_ID, BOOK_NAME) values (:id, :authorId, :name)")
                .dataSource(dataSource)
                .build();
    }

    @StepScope
    @Bean
    public JdbcBatchItemWriter<CommentDao> commentWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<CommentDao>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into COMMENTS (ID, BOOK_ID, POST_DATE, AUTHOR_NAME, CONTENT, FAVORITE) " +
                        "values (:id, :bookId, :postDate, :authorName, :content, :favorite)")
                .dataSource(dataSource)
                .build();
    }

    @StepScope
    @Bean
    public JdbcBatchItemWriter<BookGenresDao> bookGenresWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<BookGenresDao>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into BOOK_GENRES (BOOK_ID, GENRE_ID) " +
                        "values (:bookId, :genreId)")
                .dataSource(dataSource)
                .build();
    }

    /* Job */
    @Bean
    public Job importJob() {
        return jobBuilderFactory.get(IMPORT_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(importGenres())
                .next(importAuthors())
                .next(importBooks())
                .next(importComments())
                .next(importBookGenres())
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("Конец job");
                    }
                })
                .build();
    }

    /* Steps */
    @Bean
    public Step importGenres() {
        return stepBuilderFactory.get("importGenres")
                .<Genre, GenreDao>chunk(CHUNK_SIZE)
                .reader(genreMongoItemReader())
                .processor(genreSequenceProcessor(sequenceService))
                .writer(genreWriter(dataSource))
                .listener(new ItemReadListener() {
                    public void beforeRead() {
                        log.info("Начало чтения жанра");
                    }

                    public void afterRead(Object o) {
                        log.info(String.format("Конец чтения жанра: %s", ((Genre) o).getName()));
                    }

                    public void onReadError(Exception e) {
                        log.info("Ошибка чтения жанра");
                    }
                })
                .build();
    }

    @Bean
    public Step importAuthors() {
        return stepBuilderFactory.get("importAuthors")
                .<Author, AuthorDao>chunk(CHUNK_SIZE)
                .reader(authorMongoItemReader())
                .processor(authorSequenceProcessor(sequenceService))
                .writer(authorWriter(dataSource))
                .listener(new ItemReadListener() {
                    public void beforeRead() {
                        log.info("Начало чтения автора");
                    }

                    public void afterRead(Object o) {
                        log.info(String.format("Конец чтения автора: %s", ((Author) o).getName()));
                    }

                    public void onReadError(Exception e) {
                        log.info("Ошибка чтения автора");
                    }
                })
                .build();
    }

    @Bean
    public Step importBooks() {
        return stepBuilderFactory.get("importBooks")
                .<Book, BookDao>chunk(CHUNK_SIZE)
                .reader(bookMongoItemReader())
                .processor(bookSequenceProcessor(sequenceService))
                .writer(bookWriter(dataSource))
                .listener(new ItemReadListener() {
                    public void beforeRead() {
                        log.info("Начало чтения книги");
                    }

                    public void afterRead(Object o) {
                        log.info(String.format("Конец чтения книги: %s", ((Book) o).getName()));
                    }

                    public void onReadError(Exception e) {
                        log.info("Ошибка чтения книги");
                    }
                })
                .build();
    }

    @Bean
    public Step importComments() {
        return stepBuilderFactory.get("importComments")
                .<Comment, CommentDao>chunk(CHUNK_SIZE)
                .reader(commentMongoItemReader())
                .processor(commentSequenceProcessor(sequenceService))
                .writer(commentWriter(dataSource))
                .listener(new ItemReadListener() {
                    public void beforeRead() {
                        log.info("Начало чтения комментария");
                    }

                    public void afterRead(Object o) {
                        log.info("Конец чтения комментария");
                    }

                    public void onReadError(Exception e) {
                        log.info("Ошибка чтения комментария");
                    }
                })
                .build();
    }

    @Bean
    public Step importBookGenres() {
        return stepBuilderFactory.get("importBookGenres")
                .<BookGenresDao, BookGenresDao>chunk(CHUNK_SIZE)
                .reader(bookGenresDaoItemReader())
                .writer(bookGenresWriter(dataSource))
                .listener(new ItemReadListener() {
                    public void beforeRead() {
                        log.info("Начало чтения BookGenres");
                    }

                    public void afterRead(Object o) {
                        log.info("Конец чтения BookGenres");
                    }

                    public void onReadError(Exception e) {
                        log.info("Ошибка чтения BookGenres");
                    }
                })
                .build();
    }


}
