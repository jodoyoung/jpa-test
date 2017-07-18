package la.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import la.jpa.domain.User;
import la.jpa.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUser(String id) {
		return userRepository.findOne(id);
	}

}