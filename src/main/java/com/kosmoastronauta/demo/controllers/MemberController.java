package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class MemberController
{
    @Autowired
    MemberService memberService;

    @GetMapping(path = "/members/")
    public ResponseEntity<List<Member>> getMembers()
    {
        List<Member> members = memberService.getAllMembers();
        if(members.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
    }

    @GetMapping(path = "/member/{id}/")
    public ResponseEntity<Member> getMemberById(@PathVariable long id)
    {
        Member member = new Member();

        try
        {
            member = memberService.getMemberById(id);
        } catch(NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    @PostMapping(path = "/members/")
    public ResponseEntity<Member> addMember(@RequestBody Member member)
    {
        member.setNumberOfCurrentlyBorrowedBooks(0);
        memberService.addMember(member);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    @DeleteMapping(path = "/member/{id}/")
    public void deleteMember(@PathVariable int id) {memberService.deleteMemberById(id);}
}