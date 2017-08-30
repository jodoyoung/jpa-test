package la.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.annotation.JsonView;
import la.jpa.domain.User;
import la.jpa.service.OrgService;
import la.jpa.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value= "/user" , method= RequestMethod.GET)
	public @ResponseBody List<User> user() {
		return userService.getUser();
	}

	@RequestMapping(value = "/adduser", method= RequestMethod.POST)
	public @ResponseBody User add(String name) {
		return userService.addUser(name);
	}

	@RequestMapping(value = "/moduser", method= RequestMethod.POST)
	public @ResponseBody User modUser(String id,String name, String orgId) {
		return userService.modUser(id, name, orgId);
	}
}
