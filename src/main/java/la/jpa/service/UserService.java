package la.jpa.service;

import java.util.List;

import la.jpa.domain.User;
import la.jpa.model.UserModel;

public interface UserService {

    User createUser(UserModel model);

    User updateUser(UserModel model);

    void deleteUser(Long id);

    User getUser(Long id);

    List<User> getUsers();

}
