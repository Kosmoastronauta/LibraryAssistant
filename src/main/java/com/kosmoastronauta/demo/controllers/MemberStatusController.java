package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.domain.ReservationInfoPerMember;
import com.kosmoastronauta.demo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberStatusController
{
    @Autowired
    MemberService memberService;

    @GetMapping(path = "/member/{id}/booksToReturn/")
    public ResponseEntity<List<ReservationInfoPerMember>> getNotReturnedBooksByMemberId(@PathVariable long id)
    {
        try
        {
            List<ReservationInfoPerMember> infos = memberService.getInfoAboutNotReturnedBooksByMemberId(id);
        } catch(NullPointerException e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(memberService.getInfoAboutNotReturnedBooksByMemberId(id),
                HttpStatus.OK);
    }

    @PostMapping(path = "/members/search/")
    public ResponseEntity<List<Member>> getMembersByInput(@RequestBody Member member)
    {
        if(isEmpty(member)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(onlyName(member)) return new ResponseEntity<>(memberService.getMembersByOnlyName(member), HttpStatus.OK);

        if(onlyLastName(member)) return new ResponseEntity<>(memberService.getMembersByOnlyLastName(member), HttpStatus.OK);

        else return new ResponseEntity<>(memberService.getMembersByNameAndLastName(member), HttpStatus.OK);
    }

    protected static boolean onlyName(Member member)
    {
        return member.getName() != null && member.getLastName() == null;
    }

    protected static boolean onlyLastName(Member member)
    {
        return member.getLastName() != null && member.getName() == null;
    }

    protected static boolean isEmpty(Member member)
    {
        return member.getName() == null && member.getLastName() == null;
    }
}