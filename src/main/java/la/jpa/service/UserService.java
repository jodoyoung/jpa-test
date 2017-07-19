package la.jpa.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import la.jpa.domain.User;
import la.jpa.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User getUser(String id) {
		return userRepository.findOne(id);
	}

}