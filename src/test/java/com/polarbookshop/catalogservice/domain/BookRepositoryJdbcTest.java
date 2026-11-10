package com.polarbookshop.catalogservice.domain;

import java.util.List;
import java.util.Optional;

import com.polarbookshop.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class) // auditing is not part of the slice by default
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// do not swap in an embedded database
@ActiveProfiles("integration") // use the Testcontainers URL
class BookRepositoryJdbcTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate; // lower-level setup tool

    @Test
    void findBookByIsbnWhenExisting() {
        var isbn = "1234561237";
        var book = Book.of(isbn, "Title", "Author", 12.90);
        jdbcAggregateTemplate.insert(book); // arrange without the repository under test

        Optional<Book> actual = bookRepository.findByIsbn(isbn);

        assertThat(actual).isPresent();
        assertThat(actual.get().isbn()).isEqualTo(isbn);
    }

    @Test
    void findBookByIsbnWhenNotExisting() {
        assertThat(bookRepository.findByIsbn("1234561238")).isEmpty();
    }

    @Test
    void findAllReturnsBooks() {
        var book = Book.of("1234561237", "Title", "Author", 12.90);
        var book2 = Book.of("1234561238", "Title2", "Author2", 13.90);
        jdbcAggregateTemplate.insert(book);
        jdbcAggregateTemplate.insert(book2);

        var actual = bookRepository.findAll();

        assertThat(actual)
                .extracting(Book::isbn)
                .containsExactlyInAnyOrder(book.isbn(), book2.isbn());
        // I had to look up the syntax for this assert ^
    }

    @Test
    void existsByIsbnIsFalse() {
        var book = Book.of("1234561237", "Title", "Author", 12.90);
        jdbcAggregateTemplate.insert(book);
        boolean actual = bookRepository.existsByIsbn("0000000000");
        assertThat(actual).isEqualTo(false);
    }

    @Test
    void existByIsbnIsTrue() {
        var book = Book.of("1234561237", "Title", "Author", 12.90);
        jdbcAggregateTemplate.insert(book);
        boolean actual = bookRepository.existsByIsbn("1234561237");
        assertThat(actual).isEqualTo(true);
    }

    @Test
    void deleteByIsbn() {
        var book = Book.of("1234561237", "Title", "Author", 12.90);
        var insertedBook = jdbcAggregateTemplate.insert(book);
        bookRepository.deleteByIsbn("1234561237");

        assertThat(jdbcAggregateTemplate.existsById(insertedBook.id(), Book.class)).isFalse();
    }

    @Test
    void createDateGetsSetAfterSave() {
        var book = Book.of("1234561237", "Title", "Author", 12.90);

        var savedBook = bookRepository.save(book);

        assertThat(savedBook.createdDate()).isNotNull();
    }
}