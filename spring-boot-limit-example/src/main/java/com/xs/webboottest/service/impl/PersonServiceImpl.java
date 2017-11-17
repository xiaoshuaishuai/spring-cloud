package com.xs.webboottest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xs.webboottest.domain.People;
import com.xs.webboottest.repository.IPeopleRepository;
import com.xs.webboottest.service.IPersonService;
@Service
public class PersonServiceImpl implements IPersonService {
	@Autowired
	IPeopleRepository peopleRepository;
	@Override
	public List<People> findAll() {
		return peopleRepository.findAll();
	}

}
