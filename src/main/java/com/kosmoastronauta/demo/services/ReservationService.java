package com.kosmoastronauta.demo.services;

import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.domain.Reservation;
import com.kosmoastronauta.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService
{
    @Autowired
    ReservationRepository reservationRepository;

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

    public List<Reservation> getReservationByBookId(long BookId)
    {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.getReservationByBookId(BookId).forEach(reservations::add);
        return reservations;
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
        reservationRepository.deleteReservation(book.getId(),member.getId());
    }

}