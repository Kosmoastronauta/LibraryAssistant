package com.kosmoastronauta.demo.services;

import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService
{
    @Autowired
    MemberRepository memberRepository;

    public MemberRepository getMemberRepository()
    {
        return memberRepository;
    }

    public void setMemberRepository(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers()
    {
        List<Member> members = new ArrayList<>();
        memberRepository.findAll().forEach(members::add);

        return members;
    }

    public Member getMemberById(long id) { return memberRepository.findById(id).get(); }

    public void addMember(Member member){ memberRepository.save(member);}

    public void deleteMemberById(long id) { memberRepository.deleteById(id); }

}