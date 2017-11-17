package com.xs.webboottest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xs.webboottest.limiting.limit;
import com.xs.webboottest.service.ILimitExample;
import com.xs.webboottest.vo.RequestVo;
@Component
public class LimitExampleImpl implements ILimitExample{
	private static final Logger logger = LoggerFactory.getLogger(LimitExampleImpl.class);

	@limit
	@Override
	public void limitTest(RequestVo vo) {
		logger.info("业务接口=====");
	}

}
