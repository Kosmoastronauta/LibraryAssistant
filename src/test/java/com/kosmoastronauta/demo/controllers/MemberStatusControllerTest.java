package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Member;
import org.junit.Assert;
import org.junit.Test;
import org.apache.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import static io.restassured.RestAssured.given;

@ActiveProfiles("test")
public class MemberStatusControllerTest
{
    public static final String WEB = "http://localhost:8080";

    @Test
    public void onlyNameMember()
    {
        //Given
        //When
        Member member = new Member();
        member.setName("Temp");
        member.setEmail("temp");
        //Then
        Assert.assertTrue(MemberStatusController.isOnlyName(member));
    }

    @Test
    public void onlyLastNameMember()
    {
        //Given
        //When
        Member member = new Member();
        member.setLastName("Temp");
        member.setEmail("temp");
        //Then
        Assert.assertTrue(MemberStatusController.isOnlyLastName(member));
    }

    @Test
    public void EmptyMember()
    {
        //Given
        //When
        Member member = new Member();
        member.setEmail("temp");
        member.setId(0);
        //Then
        Assert.assertTrue(MemberStatusController.isEmpty(member));
    }

    @Test()
    public void GetStatusNotExistingMember()
    {
        given().when().get(WEB + "/member/0/booksToReturn/").then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    }

}
