package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.domain.Reservation;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import javax.transaction.Transactional;
import static io.restassured.RestAssured.given;

@Transactional
public class ReservationControllerTest
{
    public static final String WEB = "http://localhost:4200";

    @Test
    public void getAllReservations()
    {
        given().when().get(WEB + "/reservations/").then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getReservationByIdExistingReservation()
    {
        given().when().get(WEB + "/reservation/37/").then().assertThat().statusCode(HttpStatus.SC_OK);
    }

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
    public void getFullInfoAboutExistingReservation()
    {
        given().when().get(WEB + "/reservation/fullInfo/37/").then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void makingReservationAndReturning()
    {
        Reservation reservation = given().contentType("/application/json")
                .when().put(WEB + "/reservations" + "/makeReservation/2/16/")
                .then().statusCode(HttpStatus.SC_OK)
                .extract().as(Reservation.class);
        long bookId = reservation.getBookId();
        long reservationId = reservation.getId();

        Book book = given().contentType("application/json")
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
    }
}
