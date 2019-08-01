package com.kosmoastronauta.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String lastName;
    private String email;
    private int numberOfCurrentlyBorrowedBooks;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getNumberOfCurrentlyBorrowedBooks()
    {
        return numberOfCurrentlyBorrowedBooks;
    }

    public void setNumberOfCurrentlyBorrowedBooks(int numberOfCurrentlyBorrowedBooks)
    {
        this.numberOfCurrentlyBorrowedBooks = numberOfCurrentlyBorrowedBooks;
    }
}