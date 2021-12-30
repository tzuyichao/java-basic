package reflect.proxy.dummymock;

import java.util.Objects;

public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository repository) {
        this.bookRepository = repository;
    }

    public Book create(String name) {
        Objects.requireNonNull(name);
        Book book = new Book();
        book.setName(name);
        return bookRepository.save(book);
    }
}
