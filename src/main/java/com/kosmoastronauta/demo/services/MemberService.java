package com.kosmoastronauta.demo.services;

import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.domain.ReservationInfoPerMember;
import com.kosmoastronauta.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
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

    public Member getMemberById(long id)
    {
        Member member = memberRepository.findById(id).get();
        if(member != null) return member;

        else throw new NullPointerException("There is no member with this id");
    }

    public void addMember(Member member)
    {
        if(emailValidation(member.getEmail()))
        memberRepository.save(member);
        else throw new InvalidParameterException("Invalid email address");
    }

    public void deleteMemberById(long id) { memberRepository.deleteById(id); }

    public List<ReservationInfoPerMember> getInfoAboutNotReturnedBooksByMemberId(long id)
    {
        if(memberRepository.findById(id).isEmpty()) throw new NullPointerException("There is no member with this id!");

        List<ReservationInfoPerMember> infos = new LinkedList<>();
        int i = 0;
        List<Object[]> results = memberRepository.getInfoAboutNotReturnedBooksForMemberById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        for(Object[] result : results)
        {
            infos.add(i, new ReservationInfoPerMember(result[0].toString(), result[1].toString(), LocalDateTime.parse(result[2].toString(), formatter), LocalDateTime.parse(result[3].toString(), formatter)));
            i++;
        }

        return infos;
    }

    public List<Member> getMembersByOnlyName(Member member)
    {
        return memberRepository.getMembersByNameLike(member.getName());
    }

    public List<Member> getMembersByOnlyLastName(Member member)
    {
        return memberRepository.getMembersByLastNameLike(member.getLastName());
    }

    public List<Member> getMembersByNameAndLastName(Member member)
    {
        return memberRepository.getMembersByNameIsLikeAndAndLastNameIsLike(member.getName(), member.getLastName());
    }

    private static boolean emailValidation(String email)
    {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

}
