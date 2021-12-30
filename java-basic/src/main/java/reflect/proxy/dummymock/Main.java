package reflect.proxy.dummymock;

public class Main {
    public static void main(String[] args) {
        BookRepository mockBookRepository = Mock.createMock(BookRepository.class);
        BookService bookService = new BookService(mockBookRepository);

        bookService.create("Production Kubernetes");
        System.out.println("Save execution count: " + Mock.count(mockBookRepository, "save"));
    }
}
