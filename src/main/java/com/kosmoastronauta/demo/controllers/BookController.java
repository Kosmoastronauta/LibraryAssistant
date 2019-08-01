package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController
{
    @Autowired
    BookService bookService;

    @GetMapping(path = "/books")
    public List<Book> getBooks()
    {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/book/{id}")
    public Book getBookById(@PathVariable int id)
    {
        Book book = new Book();
        try
        {
            book = bookService.getBookById(id);

        }catch(NullPointerException e)
        {
            System.out.println("There is no book with this id");
        }
        return book;
    }

    @PostMapping(path = "/books")
    public Book addBook(@RequestBody Book book)
    {
        bookService.addBook(book);
        return book;
    }

    @DeleteMapping(path = "/book/{id}")
    public void deleteBook(@PathVariable int id)
    {
        bookService.deleteBookById(id);
    }

}