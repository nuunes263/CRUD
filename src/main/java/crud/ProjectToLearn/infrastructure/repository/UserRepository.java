package crud.ProjectToLearn.infrastructure.repository;

import crud.ProjectToLearn.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
