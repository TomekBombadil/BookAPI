package pl.coderslab.beans.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.beans.dao.MemoryBookService;
import pl.coderslab.beans.model.Book;

import java.util.List;

@RestController
@RequestMapping(value = {"/books"})
public class BookController {

    private MemoryBookService memoryBookService;

    public BookController(MemoryBookService memoryBookService) {
        this.memoryBookService = memoryBookService;
    }

    @RequestMapping(value = {"/helloBook"})
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public List<Book> allBooks() {
        return memoryBookService.findAll();
    }

    @RequestMapping(value = {"/{id:\\d+}"}, method = RequestMethod.GET)
    public Book book(@PathVariable("id") long id) {
        return memoryBookService.read(id).orElseGet(()->new Book(id, "","","","", ""));
    }

    @RequestMapping(value={""}, method=RequestMethod.POST)
    public Book addBook(@RequestBody Book book){
        return memoryBookService.create(book);
    }

    @RequestMapping(value={""}, method=RequestMethod.PUT)
    public void editBook(@RequestBody Book book){
        memoryBookService.update(book);
    }
}
