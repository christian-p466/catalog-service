package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends CrudRepository<Book, Long> {

    // Derived queries: Spring Data builds the SQL from the method name.
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    // Spring Data JDBC derives only read queries, so we write this one.
    @Modifying
    @Transactional
    @Query("delete from book where isbn = :isbn")
    void deleteByIsbn(String isbn);
}