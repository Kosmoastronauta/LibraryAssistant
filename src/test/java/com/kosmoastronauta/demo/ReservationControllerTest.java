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
    public void makingReservationTest()
    {
        given().when().put(WEB + "/reservations/makeReservation/2/16").then().statusCode(HttpStatus.SC_OK);
        Reservation reservation = given().contentType("/application/json").when().post(WEB + "/reservations" +
                "/makeReservation").then().statusCode(HttpStatus.SC_OK).extract().as(Reservation.class);
        given().when().put(WEB + "/reservation/return/" + reservation.getId()).then().statusCode(HttpStatus.SC_OK);
    }
}
