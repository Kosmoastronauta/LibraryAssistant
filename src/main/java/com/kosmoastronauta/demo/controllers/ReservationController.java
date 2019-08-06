package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Reservation;
import com.kosmoastronauta.demo.domain.ReservationFullInfo;
import com.kosmoastronauta.demo.services.BookService;
import com.kosmoastronauta.demo.services.MemberService;
import com.kosmoastronauta.demo.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ReservationController
{
    @Autowired
    MemberService memberService;

    @Autowired
    BookService bookService;

    @Autowired
    ReservationService reservationService;

    @GetMapping(path = "/reservations")
    public List<Reservation> getReservations()
    {
        return reservationService.getAllReservations();
    }

    @GetMapping(path = "/reservation/{bookId}/{memberId}")
    public ReservationFullInfo getFullInfoAboutReservation(@PathVariable("bookId") long bookId,
                                                           @PathVariable("memberId") long memberId)
    {
        return reservationService.getFullInfoAboutReservation(bookId, memberId);
    }

    @PutMapping(path = "/reservations/{bookId}/{memberId}")
    public Reservation makeReservation(@PathVariable("bookId") long bookId, @PathVariable("memberId") long memberId)
    {
        return reservationService.addReservation(bookService.getBookById(bookId),memberService.getMemberById(memberId));
    }
}