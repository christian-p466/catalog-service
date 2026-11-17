## Exercises p1

**E1. Honor the contract for PUT.** Table 1 promises `201 Created` when PUT creates a new book, but the controller always answers `200`. Fix this (for example, by returning `ResponseEntity<Book>` and letting the service tell you whether it created or updated). Add a test for both cases.
* I had to look up what a response entity is and how to handle this. It essentially wraps the book object with a status and a body. It will return 201 when creating a new book, and 200 for when updating an existing book. 

**E2. Path versus body ISBN.** What does `PUT /books/1111111111` do when the body contains `"isbn": "2222222222"` and neither book exists? Is that acceptable? Decide on a policy, implement it, document it in `README.md`, and test it.
* As it existed, the PUT would create a new book with isbn 2222222222. This is because only the request body acts as the source of truth. I updated it so that it returns a bad request if this is the case.

**E3. Real ISBN validation.** Write a custom constraint annotation `@ValidIsbn` (with a `ConstraintValidator`) that checks the ISBN-10 or ISBN-13 check digit. Unit-test it with valid and invalid ISBNs.
* skipping this one

**E4. DELETE on a missing book.** Currently it returns `204`. Argue (in two or three sentences in `README.md`) whether that is correct in light of HTTP’s idempotency semantics.
* This is not a great solution. 204's are cacheable responses by default. It might be a good idea to turn off caching for DELETE requests, otherwise we can run into a problem of DELETE on an isbn that does not exist. Then we create that isbn. The next DELETE will have a cached 204 and not delete the book.

**E5. More coverage.** Add a `@SpringBootTest` test that performs POST, then GET, then PUT, then DELETE, then GET (expecting `404`) on the same book.

## Exercises p2

**E1. Evolve the schema.** Polarso(ph)ia wants to record each book’s publisher. Add a migration `V2__Add_publisher_column.sql` that adds a nullable publisher column, add an optional publisher field to `Book` (and to `Book.of`), and update all callers and tests. Explain in `README.md` why the column must be nullable in this release, and outline the two-release plan you would follow to make it mandatory later without breaking instances still running the old version.
- in readme

**E2. Search by author.** Add `GET /books?author=...` that returns the books by that author. Implement it with a derived query method in `BookRepository`, and test it at the repository level (`@DataJdbcTest`) and the web level (`@WebMvcTest`).
- in readme
