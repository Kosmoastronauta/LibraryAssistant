package com.kosmoastronauta.demo.services;

import com.google.common.collect.Lists;
import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService
{
    @Autowired
    BookRepository bookRepository;

    public BookRepository getBookRepository()
    {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book)
    {
        bookRepository.save(book);
    }

    public List<Book> getAllBooks()
    {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);

        return books;
    }

    public Book getBookById(long id)
    {
        return bookRepository.findById(id).get();
    }

    public void deleteBookById(long id)
    {
        bookRepository.deleteById(id);
    }

}