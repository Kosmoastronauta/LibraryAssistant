package com.kosmoastronauta.demo;

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
        given().when().put(WEB + "/reservations/2/16").then().statusCode(HttpStatus.SC_OK);
    }
}
