package com.kosmoastronauta.demo;

import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class MemerControllerTest
{
    public static final String WEB = "http://localhost:8181";

    @Test
    public void GetAllMembersResponseCodeOk()
    {
        given().when().get(WEB + "/members").then().statusCode(200);
    }

    @Test
    public void addingCheckingIfExistsAndRemovingBook()
    {
        try
        {
            JSONObject request = new JSONObject();
            request.put("name", "Temp Member");
            request.put("lastName", "Temp LastName");
            request.put("email", "temp email");

            int bookId =
                    given().contentType("application/json")
                            .body(request.toString())
                            .when().post(WEB + "/members")
                            .then().statusCode(200)
                            .extract().path("id");


            given().when().get(WEB + "/member/" + bookId).then().statusCode(200);
            given().when().delete(WEB + "/member/" + bookId).then().statusCode(200);
        } catch(Exception e) {}
    }

    @Test
    public void addingNewMemberNumberOfBorrowedBooksShouldBeZero()
    {
        try
        {
            JSONObject request = new JSONObject();
            request.put("name", "Temp Member");
            request.put("lastName", "Temp LastName");
            request.put("email", "temp email");

            int numberOfCurrentlyBorrowedBooks =
                    given().contentType("application/json").
                            body(request.toString())
                            .when().post(WEB + "/members")
                            .then().statusCode(200)
                            .extract().path("numberOfCurrentlyBorowedBooks");

            given().when().delete(WEB + "/member/" + numberOfCurrentlyBorrowedBooks).then().statusCode(200);
        } catch(Exception e) {}
    }
}