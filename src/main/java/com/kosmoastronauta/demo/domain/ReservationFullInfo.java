package com.kosmoastronauta.demo.domain;

import java.time.LocalDateTime;

public class ReservationFullInfo
{
    private long reservationId;
    private Book book;
    private Member member;
    private LocalDateTime start;
    private LocalDateTime end;
    private boolean returned;

    public ReservationFullInfo(Book book, Member member, boolean returned)
    {
        this.book = book;
        this.member = member;
        this.returned = returned;
    }

    public LocalDateTime getStart()
    {
        return start;
    }

    public void setStart(LocalDateTime start)
    {
        this.start = start;
    }

    public LocalDateTime getEnd()
    {
        return end;
    }

    public void setEnd(LocalDateTime end)
    {
        this.end = end;
    }

    public long getReservationId()
    {
        return reservationId;
    }

    public void setReservationId(long reservationId)
    {
        this.reservationId = reservationId;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    public Member getMember()
    {
        return member;
    }

    public void setMember(Member member)
    {
        this.member = member;
    }

    public boolean isReturned()
    {
        return returned;
    }

    public void setReturned(boolean returned)
    {
        this.returned = returned;
    }
}