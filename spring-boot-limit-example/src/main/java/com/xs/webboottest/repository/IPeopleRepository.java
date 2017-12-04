package com.xs.webboottest.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xs.webboottest.domain.People;

public interface IPeopleRepository extends JpaRepository<People, Long>, JpaSpecificationExecutor<People> {
	@Query(value = "select sum(acount) from people", nativeQuery = true)
	BigDecimal sumPeople();
	@Query(value = "select * from people where id = (select max(id) from people)", nativeQuery = true)
	People nativePeople();
	@Query(value = "select * from people where id = (select max(id) from people) and namep=:namep", nativeQuery = true)
	People nativePeople2(@Param("namep") String namep);
}
