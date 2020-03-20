package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Book;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class BookControllerTest
{

    public static final String WEB = "http://localhost:8080";

    @Test
    public void GetBooksResponseCodeOk()
    {
        RestAssured.defaultParser = Parser.JSON;
        JSONObject request = new JSONObject();
        request.put("title", "Temp Book2!!!!");
        request.put("author", "Temp author2!!!!!");
        request.put("edition", "first2!!!!");

        Book book = given().contentType("application/json")
                .body(request.toString())
                .when().post(WEB + "/books/")
                .then().statusCode(HttpStatus.SC_OK)
                .extract().as(Book.class);

        given().when().get(WEB + "/book/" + book.getId() + "/").then().statusCode(HttpStatus.SC_OK);
        given().when().get(WEB + "/books/").then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void GetBookWhichNotExist()
    {
        given().when().get(WEB + "/book/0/").then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void addingCheckingIfExistsAndRemovingBook()
    {
        RestAssured.defaultParser = Parser.JSON;
            JSONObject request = new JSONObject();
            request.put("title", "Temp Book2!!!!");
            request.put("author", "Temp author2!!!!!");
            request.put("edition", "first2!!!!");

            Book book = given().contentType("application/json")
                    .body(request.toString())
                    .when().post(WEB + "/books/")
                    .then().statusCode(HttpStatus.SC_OK)
                    .extract().as(Book.class);

            given().when().get(WEB + "/book/" + book.getId() + "/").then().statusCode(HttpStatus.SC_OK);
            given().when().delete(WEB + "/book/" + book.getId() + "/").then().statusCode(HttpStatus.SC_OK);
            Assert.assertTrue(book.isFree());

    }

}
