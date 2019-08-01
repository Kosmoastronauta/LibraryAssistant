package com.kosmoastronauta.demo.services;


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
        reservationRepository.getReservationsByMemberId().
    }
}