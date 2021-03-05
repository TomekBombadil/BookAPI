package pl.coderslab.beans.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.beans.dao.MemoryBookService;
import pl.coderslab.beans.model.Book;

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

}