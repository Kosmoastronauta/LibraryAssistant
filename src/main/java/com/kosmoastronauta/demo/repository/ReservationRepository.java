package com.kosmoastronauta.demo.repository;


import com.kosmoastronauta.demo.domain.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long>
{
    public List<Reservation> getReservationsByBookId();

    public List<Reservation> getReservationsByMemberId(Long memberId);
}
