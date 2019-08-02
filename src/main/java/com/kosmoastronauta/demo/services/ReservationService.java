package com.kosmoastronauta.demo.services;

import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.domain.Reservation;
import com.kosmoastronauta.demo.domain.ReservationFullInfo;
import com.kosmoastronauta.demo.repository.BookRepository;
import com.kosmoastronauta.demo.repository.MemberRepository;
import com.kosmoastronauta.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public Reservation getReservationByBookId(long BookId) throws NullPointerException
    {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.getReservationByBookId(BookId).forEach(reservations::add);

        if(reservations.isEmpty()) throw new NullPointerException();

        else return reservations.get(0);
    }

    public Reservation getReservationById(long id)
    {
        return reservationRepository.findById(id).get();
    }

    public void addReservation(Book book, Member member)
    {
        Reservation reservation = new Reservation(book, member);
        reservationRepository.save(reservation);
    }

    public void deleteReservation(Book book, Member member)
    {
        reservationRepository.deleteReservation(book.getId(), member.getId());
    }

    public ReservationFullInfo getFullInfoAboutReservation(Book book, Member member)
    {
        book = bookService.getBookById(book.getId()); // To fill all information about book
        member = memberService.getMemberById(member.getId()); // same as above
        ReservationFullInfo reservationFullInfo = new ReservationFullInfo(book, member);
        Reservation reservation = this.getReservationByBookId(book.getId()); //because relation between Reservation
        // and Book is "One to One"

        reservationFullInfo.setStart(reservation.getStart());
        reservationFullInfo.setEnd(reservation.getEnd());

        return reservationFullInfo;
    }
}