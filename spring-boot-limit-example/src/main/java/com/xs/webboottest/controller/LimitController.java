package com.xs.webboottest.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xs.webboottest.service.ILimitExample;
import com.xs.webboottest.vo.RequestVo;

@Controller
public class LimitController {
	@Autowired
	private ILimitExample limitExample;

	@RequestMapping("/redisLua")
	public  @ResponseBody String redisLuaLimit() {
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
		executorService.shutdown();
		return "success";
	}
}
