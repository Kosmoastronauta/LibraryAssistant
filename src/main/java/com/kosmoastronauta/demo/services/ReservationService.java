package com.kosmoastronauta.demo.services;

import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.domain.Reservation;
import com.kosmoastronauta.demo.domain.ReservationFullInfo;
import com.kosmoastronauta.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationService
{
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    BookService bookService;

    @Autowired
    MemberService memberService;

    public List<Reservation> getAllReservations()
    {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.findAll().forEach(reservations::add);

        return reservations;
    }

    public List<Reservation> getReservationsByMemberId(long memberId)
    {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.getReservationsByMemberId(memberId).forEach(reservations::add);

        return reservations;
    }

    public List<Reservation> getReservationByBookId(long BookId) throws NullPointerException
    {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.getReservationsByBookId(BookId).forEach(reservations::add);

        return reservations;
    }

    public Reservation getReservationById(long id)
    {
        Reservation reservation = reservationRepository.findById(id).get();
        if(reservation == null) throw new NoSuchElementException("There is no reservation with this id");

        return reservation;
    }

    public Reservation addReservation(Book book, Member member)
    {
        if(!book.isFree()) { throw new ExceptionInInitializerError("This book is already reserved !!!"); }

        else
        {
            book.setFree(false);
            Reservation reservation = new Reservation(book, member);
            reservationRepository.save(reservation);

            return reservation;
        }
    }

    public ReservationFullInfo getFullInfoAboutReservation(long reservationId)
    {
        Reservation reservation = this.getReservationById(reservationId);
        if(reservation == null) throw new NoSuchElementException("There is no reservation with this id");

        Book book = bookService.getBookById(reservation.getBookId()); // To fill all information about book
        Member member = memberService.getMemberById(reservation.getMemberId()); // same as above
        boolean returnedStatus = reservationRepository.getReturnedStatusByReservationId(reservationId);

        ReservationFullInfo reservationFullInfo = new ReservationFullInfo(book, member, returnedStatus);

        reservationFullInfo.setStart(reservation.getStart());
        reservationFullInfo.setEnd(reservation.getEnd());

        return reservationFullInfo;
    }

    public void returnBook(long reservationId)
    {
        long bookId = reservationRepository.getBookIdByReservationId(reservationId);
        Book book = bookService.getBookById(bookId);
        book.setFree(true);
        bookService.addBook(book);
        Reservation reservation = reservationRepository.findById(reservationId).get();
        reservation.setReturned(true);
        reservationRepository.save(reservation);
    }
}