package com.kosmoastronauta.demo.services;

import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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

    public List<Book> getAvaliableBooksOnlyByTitle(Book book)
    {
        return bookRepository.getBooksByFreeIsTrueAndTitleEquals(book.getTitle());
    }

    public List<Book> getAvaliableBooksOnlyByAuthor(Book book)
    {
        return bookRepository.getBooksByFreeIsTrueAndAuthorEquals(book.getAuthor());
    }

    public List<Book> getAvaliableBooksByTitleAndAuthor(Book book)
    {
        return bookRepository.getBooksByFreeIsTrueAndTitleEqualsAndAuthorEquals(book.getTitle(),book.getAuthor());
    }

    public Book getBookById(long id)
    {
        return bookRepository.findById(id).get();
    }

    public void deleteBookById(long id)
    {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByTitle(Book book)
    {
        System.out.println(book.getTitle());
        return bookRepository.getBooksByTitleLike(book.getTitle());
    }

    public List<Book> getBooksByAuthor(Book book)
    {
        return bookRepository.getBooksByAuthorLike(book.getAuthor());
    }

    public List<Book> getBooksByTitleAndAuthor(Book book)
    {
        return bookRepository.getBooksByTitleIsLikeAndAndAuthorIsLike(book.getTitle(), book.getAuthor());
    }

}
