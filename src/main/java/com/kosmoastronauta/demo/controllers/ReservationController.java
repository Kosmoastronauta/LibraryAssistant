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

    @GetMapping(path = "/reservation/{bookId}/{memberId}")
    public ReservationFullInfo getFullInfoAboutReservation(@PathVariable("bookId") long bookId, @PathVariable("memberId") long memberId)
    {
        return reservationService.getFullInfoAboutReservation(bookId, memberId);
    }

    @PutMapping(path = "/reservations/{bookId}/{memberId}")
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

    @DeleteMapping(path = "/reservation/{id}")
    public void deleteReservationById(@PathVariable long id)
    {
        reservationService.deleteReservationById(id);
    }


    @PostMapping(path = "/reservation/return/{id}")
    public void returnBook(@PathVariable long id)
    {

    }
}