package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class BookStatusController
{
    @Autowired
    BookService bookService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/books/avaliable/")
    public ResponseEntity<List<Book>> getAvaliableBooksByTitle(@RequestBody Book book)
    {
        if(isEmpty(book)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(isOnlyAuthor(book))
        { return new ResponseEntity<>(bookService.getAvaliableBooksOnlyByAuthor(book), HttpStatus.OK); }

        if(isOnlyTitle(book))
        { return new ResponseEntity<>(bookService.getAvaliableBooksOnlyByTitle(book), HttpStatus.OK); }

        else { return new ResponseEntity<>(bookService.getAvaliableBooksByTitleAndAuthor(book), HttpStatus.OK); }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/books/search/")
    public ResponseEntity<List<Book>> getBooksByData(@RequestBody Book book)
    {
        if(isEmpty(book)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(isOnlyAuthor(book))
        { return new ResponseEntity<>(bookService.getBooksByAuthor(book), HttpStatus.OK); }

        if(isOnlyTitle(book))
        { return new ResponseEntity<>(bookService.getBooksByTitle(book), HttpStatus.OK); }

        else { return new ResponseEntity<>(bookService.getBooksByTitleAndAuthor(book), HttpStatus.OK); }
    }

    protected static boolean isOnlyTitle(Book book)
    {
        return book.getTitle() != null && (book.getAuthor() == null || book.getAuthor() == "");
    }

    protected static boolean isOnlyAuthor(Book book)
    {
        return (book.getTitle() == null || book.getTitle() == "") && book.getAuthor() != null;
    }

    protected static boolean isEmpty(Book book)
    {
        return (book.getTitle() == null || book.getTitle() == "") && (book.getAuthor() == null || book.getAuthor() == "");
    }
}
