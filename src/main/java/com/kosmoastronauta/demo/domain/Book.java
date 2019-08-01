package com.kosmoastronauta.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)

public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Book(String title, String author, String edition)
    {
        this.title = title;
        this.author = author;
        this.edition = edition;
    }

    public Book()
    {
    }

    private String title;
    private String author;
    private String edition;

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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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