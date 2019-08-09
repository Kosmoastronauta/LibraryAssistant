package com.kosmoastronauta.demo.controllers;

import org.junit.Test;
import org.apache.http.HttpStatus;
import javax.transaction.Transactional;
import static io.restassured.RestAssured.given;

@Transactional
public class MemberStatusControllerTest
{
    public static final String WEB = "http://localhost:8181";


    @Test()
    public void GetStatusExistingMember()
    {
        given().when().get(WEB + "/member/16/booksToReturn/").then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test()
    public void GetStatusNotExistingMember()
    {
        given().when().get(WEB + "/member/0/booksToReturn/").then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    }



}
