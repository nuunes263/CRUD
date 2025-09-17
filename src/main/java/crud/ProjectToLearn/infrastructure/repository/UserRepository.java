package crud.ProjectToLearn.infrastructure.repository;

import crud.ProjectToLearn.domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository <User, Long> {
    UserDetails findByLogin(String login);
}
