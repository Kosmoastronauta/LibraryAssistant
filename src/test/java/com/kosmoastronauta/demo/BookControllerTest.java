package com.kosmoastronauta.demo;

import com.kosmoastronauta.demo.domain.Book;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.core.IsEqual.equalTo;
import static io.restassured.path.xml.XmlPath.given;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BookControllerTest
{

    public static final String WEB = "http://localhost:8181";

    @Test
    public void GetBooksResponseCodeOk()
    {
        given().when().get(WEB + "/books").then().statusCode(200);
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

            int bookId =
                    given().contentType("application/json").body(request.toString()).when().post(WEB + "/books").then().statusCode(200).extract().path("id");
            System.out.println(bookId);

            given().when().get(WEB + "/book/" + bookId).then().statusCode(200);
            given().when().delete(WEB + "/book/" + bookId).then().statusCode(200);
        } catch(Exception e) {}

    }
}