package la.jpa.service;

import java.util.SortedSet;
import java.util.TreeSet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import la.jpa.domain.Org;
import la.jpa.domain.User;
import la.jpa.repository.OrgRepository;
import la.jpa.repository.UserRepository;

@Service
@Transactional
public class OrgService {

	@Autowired
	private OrgRepository orgRepository;

	@Transactional
	public Org getOrg(String id) {
		return orgRepository.findOne(id);
	}

	@Transactional(readOnly = false)
	public Org addOrg(String name) {
		Org org = new Org();
		org.setName(name);
		return orgRepository.save(org);
	}

}