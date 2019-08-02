package com.kosmoastronauta.demo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long bookId;
    private long memberId;
    private LocalDateTime start;
    private LocalDateTime end;

    public Reservation(Book book, Member member)
    {
        this.bookId = book.getId();
        this.memberId = member.getId();
        this.start = LocalDateTime.now();
        end = start.plusMonths(1);
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getBookId()
    {
        return bookId;
    }

    public void setBookId(long bookId)
    {
        this.bookId = bookId;
    }

    public long getMemberId()
    {
        return memberId;
    }

    public void setMemberId(long memberId)
    {
        this.memberId = memberId;
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
}