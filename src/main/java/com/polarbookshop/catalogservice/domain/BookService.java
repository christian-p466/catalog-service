package com.polarbookshop.catalogservice.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // Constructor injection: the dependency is required, final, and never null.
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }
    public ResponseEntity<Book> editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existing -> {
                    var updated = new Book(existing.isbn(),
                            book.title(), book.author(), book.price());
                    return ResponseEntity.ok(bookRepository.save(updated));
                })
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(addBookToCatalog(book)));
    } // had to look up how to do this
}