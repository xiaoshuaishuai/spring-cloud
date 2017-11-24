package org.my.spring.customer;

import org.my.spring.cloud.commons.User;
import org.my.spring.customer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:9001/getstruser
 */
@RestController
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/getstruser", method = RequestMethod.GET)
	public String getUser() {
		User u = userService.getUser();
		return u.toString();
	}
}
