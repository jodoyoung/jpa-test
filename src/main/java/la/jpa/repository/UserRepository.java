package la.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import la.jpa.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
