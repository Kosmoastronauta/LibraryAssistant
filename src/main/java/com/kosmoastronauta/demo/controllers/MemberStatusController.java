package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.domain.ReservationInfoPerMember;
import com.kosmoastronauta.demo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MemberStatusController
{
    private final MemberService memberService;

    @Autowired
    public MemberStatusController(MemberService memberService) {this.memberService = memberService;}


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/member/{id}/booksToReturn/")
    public ResponseEntity<List<ReservationInfoPerMember>> getNotReturnedBooksByMemberId(@PathVariable long id)
    {
        List<ReservationInfoPerMember> infos;
        try
        {
            infos = memberService.getInfoAboutNotReturnedBooksByMemberId(id);
        } catch(NullPointerException e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(infos,
                HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/members/search/")
    public ResponseEntity<List<Member>> getMembersByInput(@RequestBody Member member)
    {
        if(isEmpty(member)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(isOnlyName(member)) return new ResponseEntity<>(memberService.getMembersByOnlyName(member), HttpStatus.OK);

        if(isOnlyLastName(member)) return new ResponseEntity<>(memberService.getMembersByOnlyLastName(member), HttpStatus.OK);

        else return new ResponseEntity<>(memberService.getMembersByNameAndLastName(member), HttpStatus.OK);
    }

    protected static boolean isOnlyName(Member member)
    {
        return member.getName() != null && (member.getLastName() == null || member.getLastName().equals(""));
    }

    protected static boolean isOnlyLastName(Member member)
    {
        return member.getLastName() != null && (member.getName() == null || member.getName().equals(""));
    }

    protected static boolean isEmpty(Member member)
    {
        return member.getName() == null && member.getLastName() == null;
    }
}
