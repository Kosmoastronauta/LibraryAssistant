package com.kosmoastronauta.demo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    long bookId;
    long memberId;
    LocalDateTime start;
    LocalDateTime end;
}
