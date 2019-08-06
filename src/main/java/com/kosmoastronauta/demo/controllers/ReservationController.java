package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.domain.Reservation;
import com.kosmoastronauta.demo.domain.ReservationFullInfo;
import com.kosmoastronauta.demo.services.BookService;
import com.kosmoastronauta.demo.services.MemberService;
import com.kosmoastronauta.demo.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/reservation/{id}")
    public Reservation getReservationById(@PathVariable long id)
    {
        return reservationService.getReservationById(id);
    }

    @GetMapping(path = "/reservation/fullInfo/{id}")
    public ReservationFullInfo getFullInfoAboutReservation(@PathVariable("id") long id)
    {
        return reservationService.getFullInfoAboutReservation(id);
    }

    @PutMapping(path = "/reservations/makeReservation/{bookId}/{memberId}")
    public Reservation makeReservation(@PathVariable("bookId") long bookId, @PathVariable("memberId") long memberId)
    {
        Reservation reservation = new Reservation();
        try
        {
            reservation = reservationService.addReservation(bookService.getBookById(bookId),
                memberService.getMemberById(memberId));
        }catch(ExceptionInInitializerError e)
        {
            System.out.println("This book is already reserved");
            throw new ExceptionInInitializerError("This book is already reserved");
        }
        return reservation;
    }

    @PostMapping(path = "/reservation/return/byId/{reservationId}")
    public void returnBook(@PathVariable long reservationId)
    {
        reservationService.returnBook(reservationId);
    }
}