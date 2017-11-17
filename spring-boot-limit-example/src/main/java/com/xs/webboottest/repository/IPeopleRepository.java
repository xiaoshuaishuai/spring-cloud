package com.xs.webboottest.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.xs.webboottest.domain.People;

public interface IPeopleRepository extends JpaRepository<People, Long>, JpaSpecificationExecutor<People> {
	@Query(value = "select sum(acount) from people", nativeQuery = true)
	BigDecimal sumPeople();
}
