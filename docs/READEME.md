# p1
## E2
* As it existed, the PUT would create a new book with isbn 2222222222. This is because only the request body acts as the source of truth. I updated it so that it returns a bad request if this is the case.

## E4
* This is not a great solution. 204's are cacheable responses by default. It might be a good idea to turn off caching for DELETE requests, otherwise we can run into a problem of DELETE on an isbn that does not exist. Then we create that isbn. The next DELETE will have a cached 204 and not delete the book.


# p2
## E1
- The column must be nullable for this release because if it was a required column, the migration would fail. There would be books in the table that don't have a publisher, and we couldn't add the publisher col. The two-release plan would be back-filling the publisher for each book in the catalog. Then once all the publishers are non-null, we could make the column required. 

## E2
- 