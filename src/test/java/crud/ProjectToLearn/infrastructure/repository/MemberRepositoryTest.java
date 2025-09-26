package crud.ProjectToLearn.infrastructure.repository;

import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequest;
import crud.ProjectToLearn.domain.Entity.Member;
import crud.ProjectToLearn.domain.Enums.Plan;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;


    @Test
    @DisplayName("Should get member sucessfully from DB")
    void findByEmailCase1() {

        //Arrange
        var memberRequest = new MemberRequest(1L, "tiago@gmail.com", "Tiago", LocalDate.of(2000,04,21), "33523145632", Plan.ANNUAL, "11955761234", null);
        this.createMember(memberRequest);

        //Act
        Optional<Member> result = memberRepository.findByEmail(memberRequest.email());

        //Assert
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get member from DB when member not exist")
    void findByEmailCase2() {

        //Arrange
        var memberRequest = new MemberRequest(1L, "tiago@gmail.com", "Tiago", LocalDate.of(2000,04,21), "33523145632", Plan.ANNUAL, "11955761234", null);

        //Act
        Optional<Member> result = memberRepository.findByEmail(memberRequest.email());

        //Assert
        assertThat(result.isEmpty()).isTrue();
    }

    private Member createMember(MemberRequest memberRequest){
        var member = new Member(memberRequest);
        this.entityManager.persist(member);
        return member;
    }
}