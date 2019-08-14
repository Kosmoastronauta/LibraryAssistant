package com.kosmoastronauta.demo.domain;

import java.time.LocalDateTime;

public class ReservationInfo
{
    private long id;

    private long bookId;
    private long memberId;
    private LocalDateTime start;
    private LocalDateTime end;
    private boolean returned;

    public ReservationInfo(long id, long bookId, long memberId, LocalDateTime start, LocalDateTime end)
    {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.start = start;
        this.end = end;
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

    public boolean isReturned()
    {
        return returned;
    }

    public void setReturned(boolean returned)
    {
        this.returned = returned;
    }
}
