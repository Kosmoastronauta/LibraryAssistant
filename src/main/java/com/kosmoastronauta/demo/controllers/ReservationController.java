package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Reservation;
import com.kosmoastronauta.demo.domain.ReservationFullInfo;
import com.kosmoastronauta.demo.services.BookService;
import com.kosmoastronauta.demo.services.MemberService;
import com.kosmoastronauta.demo.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/reservations/")
    public ResponseEntity<List<Reservation>> getReservations()
    {
        List<Reservation> reservations = reservationService.getAllReservations();

        if(!reservations.isEmpty())
        return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/reservation/{id}/")
    public ResponseEntity<Reservation> getReservationById(@PathVariable long id)
    {
        Reservation reservation = reservationService.getReservationById(id);
        if(reservation!=null) return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/reservation/fullInfo/{id}/")
    public ResponseEntity<ReservationFullInfo> getFullInfoAboutReservation(@PathVariable("id") long id)
    {
        ReservationFullInfo reservationFullInfo = reservationService.getFullInfoAboutReservation(id);

        if(reservationFullInfo != null)
        { return new ResponseEntity<ReservationFullInfo>(reservationFullInfo, HttpStatus.OK); }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping(path = "/reservations/makeReservation/{bookId}/{memberId}/")
    public ResponseEntity<Reservation> makeReservation(@PathVariable("bookId") long bookId,
                                                      @PathVariable("memberId") long memberId)
    {
        Reservation reservation = new Reservation();
        try
        {
            reservation = reservationService.addReservation(bookService.getBookById(bookId),
                memberService.getMemberById(memberId));
        }catch(ExceptionInInitializerError e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }

    @PostMapping(path = "/reservation/return/byId/{reservationId}/")
    public void returnBook(@PathVariable long reservationId)
    {
        reservationService.returnBook(reservationId);
    }
}