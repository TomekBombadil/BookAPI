package pl.coderslab.beans.dao;

import org.springframework.stereotype.Component;
import pl.coderslab.beans.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MemoryBookService implements BookService{

    private List<Book> bookList;
    private static long nextId = 7L;

    public MemoryBookService() {
        bookList = new ArrayList<>();
        bookList.add(new Book(2L, "56566569", "Book2", "Author2", "Publisher2", "Sci-Fi"));
        bookList.add(new Book(3L, "67786887", "Book3", "Author3", "Publisher3", "Action"));
        bookList.add(new Book(4L, "43433456", "Book4", "Author4", "Publisher4", "Cooking"));
        bookList.add(new Book(5L, "43436898", "Book5", "Author5", "Publisher5", "Travel"));
        bookList.add(new Book(6L, "13245655", "Book6", "Author6", "Publisher6", "Hobby"));
    }

    public List<Book> findAll() {
        return bookList;
    }

    public Optional<Book> read(long id) {
        return bookList.stream()
                .filter(book -> id == (long) book.getId())
                .findFirst();
    }

    public Book create(Book book) {
        book.setId(nextId);
        bookList.add(book);
        nextId += 1;
        return book;
    }

    public void update(Book book) {
        Book foundBook = read(book.getId()).orElse(null);
        if (foundBook != null) {
            foundBook.setIsbn(book.getIsbn());
            foundBook.setTitle(book.getTitle());
            foundBook.setAuthor(book.getAuthor());
            foundBook.setPublisher(book.getPublisher());
            foundBook.setType(book.getType());
        }
    }

    public void delete(long id) {
        Book foundBook = read(id).orElse(null);
        if (foundBook != null) {
            bookList.remove(foundBook);
        }
    }

}
