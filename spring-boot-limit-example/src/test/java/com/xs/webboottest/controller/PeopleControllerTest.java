package com.xs.webboottest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xs.webboottest.domain.People;
import com.xs.webboottest.repository.IPeopleRepository;
import com.xs.webboottest.repository.PeopleRepositoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleControllerTest {
	@Autowired
	IPeopleRepository peopleRepository;
	@Autowired
	PeopleRepositoryImpl peopleRepositoryImpl;

	@Test
	public void list() {

		List<People> list = peopleRepository.findAll();
		list.forEach(p -> {
			System.out.println(p.toString());
		});
	}

	@Test
	public void count() {

		System.out.println(peopleRepository.count());
	}

	@Test
	public void sumStatic() {
		System.out.println(peopleRepository.sumPeople().toString());
	}
	@Test
	public void nativePeople() {
		System.out.println(peopleRepository.nativePeople().toString());
	}
	@Test
	public void nativePeople2() {
		System.out.println(peopleRepository.nativePeople2("xx3").toString());
	}

	@Test
	public void sum() {
		Map<String, Object> conds = new HashMap<String, Object>();
		System.out.println(peopleRepositoryImpl.sumAmount(conds).toString());

		// peopleRepository.findAll(new Specification<People>() {
		//
		// @Override
		// public Predicate toPredicate(Root<People> root, CriteriaQuery<?>
		// query, CriteriaBuilder cb) {
		// List<Predicate> predicates = new ArrayList<>();cb.cou
		// predicates.add(cb.sum(root.get("acount")));
		// return criteriaBuilder.and(predicates.toArray(new
		// Predicate[predicates.size()]));
		// }
		// });
	}

	@Test
	public void sumCondition() {
		Map<String, Object> conds = new HashMap<String, Object>();
		conds.put("namep", "xx3");
		conds.put("id", "2");
		System.out.println(peopleRepositoryImpl.sumAmount(conds).toString());

	}
}
