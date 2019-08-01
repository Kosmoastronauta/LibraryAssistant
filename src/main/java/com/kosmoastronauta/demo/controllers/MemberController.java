package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MemberController
{
    @Autowired
    MemberService memberService;

    @GetMapping(path = "/members")
    public List<Member> getMembers() { return memberService.getAllMembers(); }

    @GetMapping(path = "/member/{id}")
    public Member getMemberById(@PathVariable int id)
    {
        Member member = new Member();

        try
        {
            member = memberService.getMemberById(id);
        } catch(NullPointerException e)
        {
            System.out.println("There is no member with this id");
        }
        return member;
    }

    @PostMapping(path = "/members")
    public Member addMember(@RequestBody Member member)
    {
        member.setNumberOfCurrentlyBorrowedBooks(0);
        memberService.addMember(member);
        return member;
    }

    @DeleteMapping(path = "member/{id}")
    public void deleteMember(@PathVariable int id) {memberService.deleteMemberById(id);}

}