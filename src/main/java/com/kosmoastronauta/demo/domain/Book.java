package com.kosmoastronauta.demo.domain;

import javax.persistence.*;

@Entity
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Book(String title, String author, String edition)
    {
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.free = true;
    }

    public Book() {}

    private String title;
    private String author;
    private String edition;
    private boolean free; // true - free, false - borrowed

    public boolean isFree() { return free; }

    public void setFree(boolean free) { this.free = free; }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getEdition()
    {
        return edition;
    }

    public void setEdition(String edition)
    {
        this.edition = edition;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return this.title + " " + this.author + " " + this.edition;
    }
}