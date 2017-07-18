package la.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import la.jpa.domain.User;
import la.jpa.model.UserModel;
import la.jpa.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(UserModel model) {

        //        return new User(model);
        User entity = userRepository.save(new User(model));
        return entity;
    }

    @Override
    @Transactional
    public User updateUser(UserModel model) {
        User user = userRepository.findOne(model.getId());
        user.setEmail(model.getEmail());
        user.setType(model.getType());
        user.setPassword(model.getPassword());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
