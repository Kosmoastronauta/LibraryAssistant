package com.kosmoastronauta.demo.repository;

import com.kosmoastronauta.demo.domain.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long>
{
    public List<Reservation> getReservationByBookId(long id);

    public List<Reservation> getReservationsByMemberId(long memberId);

    @Query(value = "SELECT reservation.returned FROM reservation WHERE reservation.id =:reservationId",
            nativeQuery = true)
    public boolean getReturnedStatusByReservationId(@Param("reservationId") long reservationId);

    @Query(value = "SELECT reservation.book_id FROM reservation  WHERE  reservation.id =:reservationId", nativeQuery =
            true)
    public long getBookIdByReservationId(@Param("reservationId") long reservationId);

    @Query(value = "SELECT reservation.member_Id FROM reservation WHERE reservation.id =:memberId", nativeQuery = true)
    public long getMemberIdByReservationId(@Param("memberId") long memberId);

}