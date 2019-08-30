package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.domain.Reservation;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import static io.restassured.RestAssured.given;

@ActiveProfiles("test")
public class ReservationControllerTest
{
    public static final String WEB = "http://localhost:8080";

    @Test
    public void getReservationByIdNotExistingReservation()
    {
        given().when().get(WEB + "/reservation/0/").then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void getFullInfoAboutNotExistingReservation()
    {
        given().when().get(WEB + "/reservation/fullInfo/0/").then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void makingReservationAndReturning()
    {
        RestAssured.defaultParser = Parser.JSON;

        JSONObject requestMember = new JSONObject();
        requestMember.put("name", "Temp Member");
        requestMember.put("lastName", "Temp LastName");
        requestMember.put("email", "temp email");

        Member member = given().contentType("application/json")
                .body(requestMember.toString())
                .when().post(WEB + "/members/")
                .then().statusCode(HttpStatus.SC_OK)
                .extract().as(Member.class);

        JSONObject requestBook = new JSONObject();
        requestBook.put("title", "Temp Book");
        requestBook.put("author", "Temp author");
        requestBook.put("edition", "first");

        Book book = given().contentType("application/json")
                .body(requestBook.toString())
                .when().post(WEB + "/books/")
                .then().statusCode(HttpStatus.SC_OK)
                .extract().as(Book.class);

        Reservation reservation = given().contentType("/application/json")
                .when().post(WEB + "/reservations" + "/makeReservation/"+ book.getId()+"/"+ member.getId()+"/")
                .then().statusCode(HttpStatus.SC_OK)
                .extract().as(Reservation.class);

        long bookId = reservation.getBookId();
        long reservationId = reservation.getId();

        book = given().contentType("application/json")
                .when().get(WEB + "/book/" + bookId + "/")
                .then().statusCode(HttpStatus.SC_OK)
                .extract().as(Book.class);
        Assert.assertFalse(book.isFree());

        //returning
        given().when().post(WEB + "/reservation/return/byId/" + reservation.getId() + "/")
                .then().statusCode(HttpStatus.SC_OK);

        book = given().contentType("application/json")
                .when().get(WEB + "/book/" + bookId + "/")
                .then().statusCode(HttpStatus.SC_OK)
                .extract().as(Book.class);

        Assert.assertTrue(book.isFree()); // checking status is free in table book
        reservation = given().when().get(WEB + "/reservation/" + reservationId + "/")
                .then().statusCode(HttpStatus.SC_OK)

                .extract().as(Reservation.class);
        Assert.assertTrue(reservation.isReturned()); // checking status isReturned in table reservation

        given().when().get(WEB + "/reservation/fullInfo/"+reservation.getId()+"/").then().assertThat().statusCode(HttpStatus.SC_OK);
        given().when().get(WEB + "/reservations/").then().statusCode(HttpStatus.SC_OK);
    }
}
