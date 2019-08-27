package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Member;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import static io.restassured.RestAssured.given;

@ActiveProfiles("test")
public class MemberControllerTest
{
    public static final String WEB = "http://localhost:8080";

    @Test
    public void GetBookWhichNotExist()
    {
        given().when().get(WEB + "/member/0/").then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void GetAllMembersResponseCodeOk()
    {
        given().when().get(WEB + "/members/").then().statusCode(HttpStatus.SC_NO_CONTENT);
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

            Member member = given().contentType("application/json").body(request.toString()).when().post(WEB + "/members/").then().statusCode(HttpStatus.SC_OK).extract().as(Member.class);

            given().when().get(WEB + "/member/" + member.getId() + "/").then().statusCode(200);
            given().when().delete(WEB + "/member/" + member.getId() + "/").then().statusCode(200);
            Assert.assertEquals(0, member.getNumberOfCurrentlyBorrowedBooks());
        } catch(Exception e) {}
    }
}
