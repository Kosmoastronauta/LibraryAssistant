package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.repository.MemberRepository;
import com.kosmoastronauta.demo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController
{
    @Autowired
    MemberService memberService;

    @GetMapping(path = "/members")
    public List<Member> getMembers()
    {
        return memberService.getAllMembers();
    }

}
