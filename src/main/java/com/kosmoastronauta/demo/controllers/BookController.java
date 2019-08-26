package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BookController
{
    @Autowired
    BookService bookService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/books/")
    public ResponseEntity<List<Book>> getBooks()
    {
        List<Book> books = bookService.getAllBooks();
        if(books.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/book/{id}/")
    public ResponseEntity<Book> getBookById(@PathVariable int id)
    {
        Book book;
        try
        {
            book = bookService.getBookById(id);

        } catch(NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/books/")
    public ResponseEntity<Book> addBook(@RequestBody Book book)
    {
        book.setFree(true);
        bookService.addBook(book);
        return new ResponseEntity<Book>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = "/book/{id}/")
    public void deleteBook(@PathVariable int id)
    {
        bookService.deleteBookById(id);
    }
}
