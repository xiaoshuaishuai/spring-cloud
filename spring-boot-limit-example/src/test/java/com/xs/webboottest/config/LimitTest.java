package com.xs.webboottest.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xs.webboottest.service.ILimitExample;
import com.xs.webboottest.vo.RequestVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LimitTest {
	@Autowired
	private ILimitExample limitExample;

	@Test
	public void test() {
		
		ExecutorService executorService=Executors.newCachedThreadPool();
		for (int i = 0; i < 200; i++) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
//					System.out.println(Thread.currentThread().getName()+"发起请求");
					RequestVo vo = new RequestVo();
					vo.setInterfaceId("findPerson");
					limitExample.limitTest(vo);
				}
			});
		}
		
		
	}
}
