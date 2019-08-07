package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class BookStatusController
{
    @Autowired
    BookService bookService;

    @PostMapping(path = "/books/avaliable/")
    public ResponseEntity<List<Book>> getAvaliableBooksByTitle(@RequestBody Book book)
    {
        return new ResponseEntity<List<Book>>(bookService.getAvaliableBooksByTitle(book), HttpStatus.OK);
    }

}
