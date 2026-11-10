package com.polarbookshop.catalogservice.domain;

import java.time.Instant;

import jakarta.validation.constraints.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

public record Book(

        @Id
        Long id, // technical (surrogate) primary key

        @NotBlank(message = "The book ISBN must be defined.")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$",
                message = "The ISBN format must be valid.")
        String isbn, // natural (business) key, still unique

        @NotBlank(message = "The book title must be defined.")
        String title,

        @NotBlank(message = "The book author must be defined.")
        String author,

        @NotNull(message = "The book price must be defined.")
        @Positive(message = "The book price must be greater than zero.")
        Double price,

        @CreatedDate
        Instant createdDate, // set once, on insert

        @LastModifiedDate
        Instant lastModifiedDate, // set on every insert and update

        @Version
        int version // optimistic locking
) {
        // Convenience factory: the framework fills in id, dates, and version.
        public static Book of(String isbn, String title, String author, Double price) {
                return new Book(null, isbn, title, author, price, null, null, 0);
        }
}