package la.jpa.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import la.jpa.ApiGenerator;

@Controller
public class TestController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Map<String, Object> model) throws Exception {
		model.put("apiList", ApiGenerator.getApiInfoVOList());
		return "test";
	}
}
