package com.kosmoastronauta.demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import javax.transaction.Transactional;

@RunWith(Suite.class)
@Transactional
@Suite.SuiteClasses({MemberControllerTest.class,
                    BookControllerTest.class,
                    ReservationControllerTest.class,
                    BookStatusControllerTest.class})
public class TestSuiteAll
{}