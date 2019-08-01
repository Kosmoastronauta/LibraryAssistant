package com.kosmoastronauta.demo;


import com.kosmoastronauta.demo.domain.Book;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import org.json.JSONObject;


public class BookControllerTest
{

    public static final String WEB = "http://localhost:8181";

    @Test
    public void GetBooksResponseCodeOk()
    {
        given().when().get(WEB + "/books").then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addingCheckingIfExistsAndRemovingBook()
    {
        try
        {
            JSONObject request = new JSONObject();
            request.put("title", "Temp Book");
            request.put("author", "Temp author");
            request.put("edition", "first");

            Book book = given().contentType("application/json")
                    .body(request.toString())
                    .when().post(WEB + "/books")
                    .then().statusCode(HttpStatus.SC_OK)
                    .extract().as(Book.class);

            given().when().get(WEB + "/book/" + book.getId()).then().statusCode(HttpStatus.SC_OK);
            given().when().delete(WEB + "/book/" + book.getId()).then().statusCode(HttpStatus.SC_OK);
            Assert.assertTrue(book.isFree());

        } catch(Exception e) {}

    }

}