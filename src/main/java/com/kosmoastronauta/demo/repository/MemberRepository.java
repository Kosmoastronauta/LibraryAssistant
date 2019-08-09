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

    public static final String TABLE_MEMBER = "member";
    public static final String RESERVATION_COLUMN_START = "start";
    public static final String RESERVATION_COLUMN_END = "end";
    public static final String RESERVATION_COLUMN_MEMBER_ID = "member_id";
    public static final String TABLE_RESERVATION = "reservation";
    public static final String TABLE_BOOK = "book";
    public static final String BOOK_COLUMN_TITLE = "title";
    public static final String BOOK_COLUMN_AUTHOR = "author";
    public static final String BOOK_COLUMN_ID = "id";
    public static final String MEMBER_COLUMN_ID = "id";
    public static final String MEMBER_COLUMN_NAME = "name";
    public static final String RESERVATION_COLUMN_BOOK_ID = "book_id";
    public static final String RESERVATION_COLUMN_RETURNED = "returned";

    public List<Member> getMembersByNameEquals(String name);

    public List<Member> getMembersByLastNameEquals(String lastName);

    public List<Member> getMembersByNameEqualsAndLastNameEquals(String name, String lastName);

    @Query(nativeQuery = true, value =
            "SELECT " + TABLE_BOOK + "." + BOOK_COLUMN_TITLE + ", " +
                    TABLE_BOOK+"."+BOOK_COLUMN_AUTHOR+ ", "+
                    TABLE_RESERVATION + "." + RESERVATION_COLUMN_START + ", "+
                    TABLE_RESERVATION + "." + RESERVATION_COLUMN_END +
                    " FROM " +
                    TABLE_RESERVATION +
                    " INNER JOIN " + TABLE_MEMBER +
                    " ON " +
                    TABLE_MEMBER + "." + MEMBER_COLUMN_ID + "=" + TABLE_RESERVATION + "." +RESERVATION_COLUMN_MEMBER_ID +
                    " INNER JOIN " +
                    TABLE_BOOK +
                    " ON " +
                    TABLE_BOOK + "." + BOOK_COLUMN_ID +
                    "=" +
                    TABLE_RESERVATION + "." + RESERVATION_COLUMN_BOOK_ID +
                    " WHERE " + TABLE_MEMBER + "." + MEMBER_COLUMN_ID + "=:memberId "+
                    " AND " +
                    TABLE_RESERVATION + "." + RESERVATION_COLUMN_RETURNED +
                    "= false")
    public List<Object[]> getInfoAboutNotReturnedBooksForMemberById(@Param("memberId") long memberId);

}