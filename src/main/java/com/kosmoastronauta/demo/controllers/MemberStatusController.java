package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.ReservationInfoPerMember;
import com.kosmoastronauta.demo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}