package crud.ProjectToLearn.infrastructure.repository;

import crud.ProjectToLearn.domain.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
