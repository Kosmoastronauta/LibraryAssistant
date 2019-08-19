package com.kosmoastronauta.demo.repository;

import com.kosmoastronauta.demo.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>, BookRepositoryCustom
{

    @Query(value = "SELECT * FROM book WHERE book.author LIKE CONCAT ('%', :author, '%') AND book.title LIKE CONCAT " +
            "('%', :title, '%')" , nativeQuery = true)
    public List<Book> getBooksByTitleIsLikeAndAndAuthorIsLike(@Param("title") String title,
            @Param("author") String author);

    @Query(value = "SELECT * FROM book WHERE book.title LIKE CONCAT ('%', :title, '%')", nativeQuery = true)
    public List<Book> getBooksByTitleLike(@Param("title") String title);

    @Query(value = "SELECT * FROM book WHERE book.author LIKE CONCAT ('%', :author, '%')", nativeQuery = true)
    public List<Book> getBooksByAuthorLike(String author);
    //  @Query(value ="SELECT * FROM book WHERE book.title=:titleOfBook AND book.free=true",nativeQuery = true)
    public List<Book> getBooksByFreeIsTrueAndTitleEquals(String title);

    public List<Book> getBooksByFreeIsTrueAndAuthorEquals(String author);

    public List<Book> getBooksByFreeIsTrueAndTitleEqualsAndAuthorEquals(String title, String author);
}
