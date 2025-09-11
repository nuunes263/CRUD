package crud.ProjectToLearn.infrastructure.repository;

import crud.ProjectToLearn.application.User.UserDto;
import crud.ProjectToLearn.domain.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}
