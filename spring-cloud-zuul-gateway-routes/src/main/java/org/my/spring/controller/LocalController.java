package org.my.spring.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本地跳转
 * @author ShuaishuaiXiao
 */
@RestController
public class LocalController {
	/**
	 * http://localhost:8200/api-b/333
	 * 方法描述  
	 */
	@RequestMapping(value="/local/{msg}")
	public String local(@PathVariable(value="msg") String msg) {
		return "zuul local forward..."+msg;
	}
	
}
