package com.kosmoastronauta.demo.domain;

import com.kosmoastronauta.demo.services.MemberService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Member extends MemberService
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;
    @NotNull
    @Size(min = 2, max = 30)
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
