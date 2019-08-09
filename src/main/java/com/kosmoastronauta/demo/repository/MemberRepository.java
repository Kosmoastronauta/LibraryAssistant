package com.kosmoastronauta.demo.repository;

import com.kosmoastronauta.demo.domain.Member;
import com.kosmoastronauta.demo.domain.ReservationInfoPerMember;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long>
{
    public List<Member> getMembersByNameEquals(String name);

    public List<Member> getMembersByLastNameEquals(String lastName);

    public List<Member> getMembersByNameEqualsAndLastNameEquals(String name, String lastName);

    @Query(nativeQuery = true, value =
            "SELECT book.title , book.author," +
                    " reservation" +
                    ".start, " +
                    "reservation.end" +
                    " FROM " +
                    "reservation INNER JOIN " +
                    "member ON member" + ".id=reservation.member_id INNER JOIN book " + "ON book.id = " +
                    "reservation" +
                    ".book_id WHERE member.id=:memberId AND reservation.returned=false")
    public List<Object[]> getInfoAboutNotReturnedBooksForMemberById(@Param("memberId") long memberId);

}