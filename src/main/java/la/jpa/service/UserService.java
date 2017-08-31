package la.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import la.jpa.domain.Org;
import la.jpa.domain.User;
import la.jpa.repository.OrgRepository;
import la.jpa.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrgRepository orgRepository;

	@Transactional
	public List<User> getUser() {
		return userRepository.findAll();
	}

	@Transactional()
	public User addUser(String name) {
		User user = new User();
		user.setName(name);
		return userRepository.save(user);
	}

	@Transactional()
	public User modUser(String id, String name, String orgId) {
		User user = userRepository.findOne(id);
		Org org = new Org ();
		org.setName("ppp");
//		user.setOrg(orgRepository.findOne(orgId));
		user.setOrg(org);
		user.getOrg();
		return user;
	}

}