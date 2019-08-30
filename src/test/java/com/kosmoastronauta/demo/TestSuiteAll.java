package com.kosmoastronauta.demo;

import com.kosmoastronauta.demo.controllers.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Suite.class)
@ActiveProfiles("test")
@Suite.SuiteClasses({MemberControllerTest.class,
                    BookControllerTest.class,
                    ReservationControllerTest.class,
                    BookStatusControllerTest.class,
                    MemberStatusControllerTest.class})
public class TestSuiteAll
{}
