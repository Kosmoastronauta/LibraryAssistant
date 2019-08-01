package com.kosmoastronauta.demo;

import com.kosmoastronauta.demo.domain.Book;
import com.kosmoastronauta.demo.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx =SpringApplication.run(DemoApplication.class, args);
	}
}