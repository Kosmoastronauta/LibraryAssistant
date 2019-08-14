package com.kosmoastronauta.demo.controllers;

import com.kosmoastronauta.demo.domain.Reservation;
import com.kosmoastronauta.demo.domain.ReservationFullInfo;
import com.kosmoastronauta.demo.domain.ReservationInfo;
import com.kosmoastronauta.demo.services.BookService;
import com.kosmoastronauta.demo.services.MemberService;
import com.kosmoastronauta.demo.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ReservationController
{
    @Autowired
    MemberService memberService;

    @Autowired
    BookService bookService;

    @Autowired
    ReservationService reservationService;

    @CrossOrigin(origins = "http://localhost:8282")
    @GetMapping(path = "/reservations/")
    public ResponseEntity<List<Reservation>> getReservations()
    {
        List<Reservation> reservations = reservationService.getAllReservations();

        if(!reservations.isEmpty())
        return new ResponseEntity<>(reservations, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "http://localhost:8282")
    @GetMapping(path = "/reservation/{id}/")
    public ResponseEntity<Reservation> getReservationById(@PathVariable long id)
    {
        Reservation reservation;
        try
        {
            reservation = reservationService.getReservationById(id);
        }catch(NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8282")
    @GetMapping(path = "/reservation/fullInfo/{id}/")
    public ResponseEntity<ReservationFullInfo> getFullInfoAboutReservation(@PathVariable("id") long id)
    {
        ReservationFullInfo reservationFullInfo;
        try
        {
            reservationFullInfo = reservationService.getFullInfoAboutReservation(id);
        }catch(NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

         return new ResponseEntity<>(reservationFullInfo, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8282")
    @PutMapping(path = "/reservations/makeReservation/{bookId}/{memberId}/")
    public ResponseEntity<Reservation> makeReservation(@PathVariable("bookId") long bookId,
                                                      @PathVariable("memberId") long memberId)
    {
        Reservation reservation;
        try
        {
            reservation = reservationService.addReservation(bookService.getBookById(bookId),
                memberService.getMemberById(memberId));
        }catch(ExceptionInInitializerError e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    //@CrossOrigin(origins = "http://localhost:8282")
    @GetMapping(path = "/reservation/notReturned/bookId/{bookId}/")
    public ResponseEntity<ReservationInfo> getReservationInfoAboutNotReturnedBookById(@PathVariable("bookId") long bookId)
    {
        ReservationInfo reservation;
        try
        {
            reservation = reservationService.getNotReturnedReservationByBookId(bookId);
        }catch(NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8282")
    @PostMapping(path = "/reservation/return/byId/{reservationId}/")
    public void returnBook(@PathVariable long reservationId)
    {
        reservationService.returnBook(reservationId);
    }
}