package la.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import la.jpa.domain.Org;
import la.jpa.domain.User;
import la.jpa.service.OrgService;
import la.jpa.service.UserService;

@Controller
public class OrgController {

	@Autowired
	private OrgService orgService;

	@RequestMapping(value= "/addorg", method= RequestMethod.GET)
	public @ResponseBody Org addorg(String orgname) {
		return orgService.addOrg(orgname);
	}

	@RequestMapping(value= "/org", method= RequestMethod.GET)
	public @ResponseBody Org org(String orgid) {
		return orgService.getOrg(orgid);
	}
}
