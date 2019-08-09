package com.kosmoastronauta.demo.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import java.time.LocalDateTime;
public class ReservationInfoPerMember
{
    private String title;
    private String author;
    private LocalDateTime start;
    private LocalDateTime endt;

    public ReservationInfoPerMember() { }

    public ReservationInfoPerMember(String title, String author, LocalDateTime start, LocalDateTime endt)
    {
        this.title = title;
        this.author = author;
        this.start = start;
        this.endt = endt;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public LocalDateTime getStart()
    {
        return start;
    }

    public void setStart(LocalDateTime start)
    {
        this.start = start;
    }

    public LocalDateTime getEndt()
    {
        return endt;
    }

    public void setEndt(LocalDateTime endt)
    {
        this.endt = endt;
    }
}