package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Book;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static io.restassured.RestAssured.given;

@Transactional
public class BookStatusControllerTest
{
    public static final String WEB = "http://localhost:8181";

    @Test
    public void OnlyTitleBook()
    {
        //Given
        //When
        Book book = new Book();
        book.setTitle("Temp");
        //Then
        Assert.assertTrue(BookStatusController.onlyTitle(book));
    }

    @Test
    public void OnlyAuthorBook()
    {
        //Given
        //When
        Book book = new Book();
        book.setAuthor("Temp");
        //Then
        Assert.assertTrue(BookStatusController.onlyAuthor(book));
    }

    @Test
    public void EmptyBook()
    {
        //Given
        //When
        Book book = new Book();
        book.setEdition("first");
        //Then
        Assert.assertTrue(BookStatusController.isEmpty(book));
    }

    @Test
    public void getAvaliableBooksOnlyByTitle() throws Exception
    {
        String title = "The Shinning";
        JSONObject request = new JSONObject();
        request.put("title", title);

        List<Book> books = given().contentType("application/json")
                .when().body(request.toString()).when().post(WEB + "/books/avaliable/")
                .then().extract().body().jsonPath().getList(".", Book.class);

        for (int i = 0; i < books.size(); i++)
            {
                Assert.assertEquals(title, books.get(i).getTitle());
            }
    }

    @Test
    public void getAvaliableBooksOnlyByAuthor() throws Exception
    {
        String author = "Gluchowsky";
        JSONObject request = new JSONObject();
        request.put("author", author);

        List<Book> books = given().contentType("application/json")
                .when().body(request.toString()).when().post(WEB + "/books/avaliable/")
                .then().extract().body().jsonPath().getList(".", Book.class);

        for (int i = 0; i < books.size(); i++)
        {
            Assert.assertEquals(author, books.get(i).getAuthor());
            Assert.assertTrue(books.get(i).isFree());
        }
    }

    @Test
    public void getAvaliableBooksByTitleAndAuthor() throws Exception
    {
        String author = "Stephen King";
        String title = "The Shinning";
        JSONObject request = new JSONObject();
        request.put("title", title);
        request.put("author", author);

        List<Book> books = given().contentType("application/json")
                .when().body(request.toString()).when().post(WEB + "/books/avaliable/")
                .then().extract().body().jsonPath().getList(".", Book.class);

        for (int i = 0; i < books.size(); i++)
        {
            Assert.assertEquals(author, books.get(i).getAuthor());
            Assert.assertEquals(title, books.get(i).getTitle());
            Assert.assertTrue(books.get(i).isFree());
        }
    }
}