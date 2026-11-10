package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(BookController.class)
class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "7373731394";
        given(bookService.viewBookDetails(isbn))
                .willThrow(BookNotFoundException.class);

        mockMvc.perform(get("/books/" + isbn))
                .andExpect(status().isNotFound());
    }
    @Test
    void whenPutCreatesBookThenShouldReturn201() throws Exception {
        String isbn = "0306406152";
        var book = Book.of(isbn, "Title", "Author", 9.90);

        given(bookService.editBookDetails(eq(isbn), any(Book.class)))
                .willReturn(ResponseEntity.status(HttpStatus.CREATED).body(book));

        mockMvc.perform(put("/books/{isbn}", isbn)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "isbn": "0306406152",
                              "title": "Title",
                              "author": "Author",
                              "price": 9.90,
                              "version": 0
                            }
                            """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isbn").value(isbn));
    }

    @Test
    void whenPutUpdatesBookThenShouldReturn200() throws Exception {
        String isbn = "0306406152";
        var book = Book.of(isbn, "Updated Title", "Author", 9.90);

        given(bookService.editBookDetails(eq(isbn), any(Book.class)))
                .willReturn(ResponseEntity.ok(book));

        mockMvc.perform(put("/books/{isbn}", isbn)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "isbn": "0306406152",
                              "title": "Updated Title",
                              "author": "Author",
                              "price": 9.90,
                              "version": 0
                            }
                            """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

}