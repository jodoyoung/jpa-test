package la.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import la.jpa.domain.Org;
import la.jpa.domain.User;

@Repository
public interface OrgRepository extends JpaRepository<Org, String> {

}
