package com.kosmoastronauta.demo.repository;

import com.kosmoastronauta.demo.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {}