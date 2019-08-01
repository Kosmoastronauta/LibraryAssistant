package com.kosmoastronauta.demo.repository;

import com.kosmoastronauta.demo.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>, BookRepositoryCustom
{}