package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class MemberController
{
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService)
    {
        this.memberService=memberService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/members/")
    public ResponseEntity<List<Member>> getMembers()
    {
        List<Member> members = memberService.getAllMembers();
        if(members.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/member/{id}/")
    public ResponseEntity<Member> getMemberById(@PathVariable long id)
    {
        Member member;

        try
        {
            member = memberService.getMemberById(id);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch(NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @PostMapping(path = "/members/")
    public ResponseEntity<Member> addMember(@RequestBody Member member)
    {
        try
        {
            member.setNumberOfCurrentlyBorrowedBooks(0);
            memberService.addMember(member);
            return new ResponseEntity<>(member, HttpStatus.OK);
        }catch(InvalidParameterException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = "/member/{id}/")
    public void deleteMember(@PathVariable int id) {memberService.deleteMemberById(id);}
}
