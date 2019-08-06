package com.kosmoastronauta.demo;

import com.kosmoastronauta.demo.domain.Reservation;
import org.apache.http.HttpStatus;
import org.junit.Test;

import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;

@Transactional
public class ReservationControllerTest
{
    public static final String WEB = "http://localhost:8181";

    @Test
    public void makingReservationAndReturning()
    {
        Reservation reservation = given().contentType("/application/json").when().put(WEB + "/reservations" +
                "/makeReservation/2/16").then().statusCode(HttpStatus.SC_OK).extract().as(Reservation.class);
        given().when().post(WEB + "/reservation/return/byId/" + reservation.getId()).then().statusCode(HttpStatus.SC_OK);
    }
}
