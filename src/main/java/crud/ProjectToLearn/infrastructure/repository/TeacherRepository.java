package crud.ProjectToLearn.infrastructure.repository;

import crud.ProjectToLearn.domain.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
