package com.kosmoastronauta.demo;

import com.kosmoastronauta.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("singleton")
public class Starter implements CommandLineRunner
{
    @Autowired
    BookRepository bookRepository;


    public void run(String... args)
    {
        System.out.println("Hello World");
    }

}

