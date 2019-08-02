package com.kosmoastronauta.demo.repository;

import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.domain.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long>
{
    public List<Reservation> getReservationByBookId(long id);

    public List<Reservation> getReservationsByMemberId(long memberId);


    @Query(value = "delete from Reservation where Reservation.bookId=:idOfBook and Reservation.memberId=:idOfMember",
            nativeQuery = true)
    public void deleteReservation(@Param("idOfBook") long idOfBook, @Param("idOfMember") long idOfMember);
}