package pl.coderslab.beans.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.beans.dao.BookService;
import pl.coderslab.beans.dao.MemoryBookService;
import pl.coderslab.beans.model.Book;

import java.util.List;

@RestController
@RequestMapping(value = {"/books"})
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = {"/helloBook"})
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public List<Book> allBooks() {
        return bookService.findAll();
    }

    @RequestMapping(value = {"/{id:\\d+}"}, method = RequestMethod.GET)
    public Book book(@PathVariable("id") long id) {
        return bookService.read(id).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");});
    }

    @RequestMapping(value={""}, method=RequestMethod.POST)
    public Book addBook(@RequestBody Book book){
        return bookService.create(book);
    }

    @RequestMapping(value={""}, method=RequestMethod.PUT)
    public void editBook(@RequestBody Book book){
        bookService.update(book);
    }

    @RequestMapping(value={"/{id:\\d+}"}, method=RequestMethod.DELETE)
    public void deleteBook(@PathVariable("id") long id){
        bookService.delete(id);
    }
}
