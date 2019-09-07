package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Member;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static io.restassured.RestAssured.given;

@ActiveProfiles("test")
@RunWith(Parameterized.class)
public class MemberControllerTest
{
    public static final String WEB = "http://localhost:8080";
    public static String currentEmail;

    public MemberControllerTest(String email)
    {
        currentEmail = email;
    }

    @Test
    public void GetBookWhichNotExist()
    {
        given().when().get(WEB + "/member/0/").then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void GetAllMembersResponseCodeOk()
    {
        try
        {
            JSONObject request = new JSONObject();
            request.put("name", "Temp Member");
            request.put("lastName", "Temp LastName");
            request.put("email", currentEmail);

            Member member = given().contentType("application/json").body(request.toString()).when().post(WEB + "/members/").then().statusCode(HttpStatus.SC_OK).extract().as(Member.class);

            given().when().get(WEB + "/member/" + member.getId() + "/").then().statusCode(200);
            Assert.assertEquals(0, member.getNumberOfCurrentlyBorrowedBooks());
        } catch(Exception e) {}
        given().when().get(WEB + "/members/").then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addingCheckingIfExistsAndRemovingMember()
    {
        try
        {
            JSONObject request = new JSONObject();
            request.put("name", "Temp Member");
            request.put("lastName", "Temp LastName");
            request.put("email", currentEmail);

            Member member = given().contentType("application/json").body(request.toString()).when().post(WEB + "/members/").then().statusCode(HttpStatus.SC_OK).extract().as(Member.class);

            given().when().get(WEB + "/member/" + member.getId() + "/").then().statusCode(200);
            given().when().delete(WEB + "/member/" + member.getId() + "/").then().statusCode(200);
            Assert.assertEquals(0, member.getNumberOfCurrentlyBorrowedBooks());
        } catch(Exception e) {}
    }

    @Test
    public void addingMemberInvalidEmail()
    {
        try
        {
            JSONObject request = new JSONObject();
            request.put("name", "Temp Member");
            request.put("lastName", "Temp LastName");
            request.put("email", "temp123edwac");

            Member member = given().contentType("application/json").body(request.toString()).when().post(WEB +
                    "/members/").then().statusCode(HttpStatus.SC_BAD_REQUEST).extract().as(Member.class);

            given().when().get(WEB + "/member/" + member.getId() + "/").then().statusCode(200);
            given().when().delete(WEB + "/member/" + member.getId() + "/").then().statusCode(200);
            Assert.assertEquals(0, member.getNumberOfCurrentlyBorrowedBooks());
        } catch(Exception e) {}
    }

    @Parameterized.Parameters
    public static Collection emailData()
    {
        return Arrays.asList("wenzlaff@yahoo.ca",
                "gbacon@me.com",
                "slanglois@sbcglobal.net",
                "bescoto@aol.com",
                "lridener@live.com",
                "salesgeek@msn.com",
                "matloff@aol.com",
                "paulv@msn.com",
                "munge@optonline.net",
                "mthurn@msn.com",
                "jdhildeb@yahoo.ca",
                "library@hotmail.com",
                "vlefevre@gmail.com",
                "hoangle@icloud.com",
                "arathi@me.com",
                "koudas@outlook.com",
                "mcast@live.com",
                "william@icloud.com",
                "geekgrl@yahoo.com");
    }
}
