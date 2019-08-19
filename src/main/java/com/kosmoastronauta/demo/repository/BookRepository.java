package com.kosmoastronauta.demo.repository;

import com.kosmoastronauta.demo.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>, BookRepositoryCustom
{

    public List<Book> getBooksByTitleIsLikeAndAndAuthorIsLike(String title, String author);

    public List<Book> getBooksByTitleIsLike(String title);

    public List<Book> getBooksByAuthorIsLike(String author);
    //  @Query(value ="SELECT * FROM book WHERE book.title=:titleOfBook AND book.free=true",nativeQuery = true)
    public List<Book> getBooksByFreeIsTrueAndTitleEquals(String title);

    public List<Book> getBooksByFreeIsTrueAndAuthorEquals(String author);

    public List<Book> getBooksByFreeIsTrueAndTitleEqualsAndAuthorEquals(String title, String author);
}
