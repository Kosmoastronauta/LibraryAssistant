package com.kosmoastronauta.demo;

import com.kosmoastronauta.demo.domain.Member;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import javax.transaction.Transactional;
import static io.restassured.RestAssured.given;

@Transactional
public class MemberControllerTest
{
    public static final String WEB = "http://localhost:8181";

    @Test
    public void GetAllMembersResponseCodeOk()
    {
        given().when().get(WEB + "/members/").then().statusCode(200);
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