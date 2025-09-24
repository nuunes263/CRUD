package crud.ProjectToLearn.application.Member.Command;

import crud.ProjectToLearn.application.Helper.MemberMapper;
import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequest;
import crud.ProjectToLearn.domain.Entity.Member;
import crud.ProjectToLearn.domain.Enums.Plan;
import crud.ProjectToLearn.infrastructure.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MemberCommandServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberCommandService memberCommandService;

    public MemberCommandServiceTest() {
        openMocks(this); // inicializa os @Mock e injeta em @InjectMocks
    }

    @Test
    @DisplayName("should throw exception when already exist an email equal.")
    void SaveMember() {
        //Arrange
//        var memberRequest = new MemberRequest(1L, "teste@gmail.com", "teste", LocalDate.now(), "11955702604", Plan.ANNUAL,"33514809895");
//        var member = new Member(memberRequest);
//        this.memberRepository.saveAndFlush(member);
//
//        var memberRequest2 = new MemberRequest(1L, "teste@gmail.com", "teste", LocalDate.now(), "11955702604", Plan.ANNUAL,"33514809895");
//        var member2 = new Member(memberRequest2);
//        this.memberRepository.saveAndFlush(member);

        //Act
        //Assert
    }

    @Test
    void DeleteById() {
    }

    @Test
    void UpdateMember() {
    }
}