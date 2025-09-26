package crud.ProjectToLearn.application.Member.Query;

import crud.ProjectToLearn.application.Helper.MemberMapper;
import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequest;
import crud.ProjectToLearn.domain.Entity.Member;
import crud.ProjectToLearn.domain.Enums.Plan;
import crud.ProjectToLearn.domain.Exceptions.TypeException.EmailAlreadyExistExeception;
import crud.ProjectToLearn.domain.Exceptions.TypeException.ProfileNotFoundException;
import crud.ProjectToLearn.infrastructure.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MemberQueryServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberQueryService memberQueryService;

    @Captor
    private ArgumentCaptor<Long> idMemberArgumentCaptor;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class getMemberById{

        @Test
        @DisplayName("Should get member by id with sucess")
        void shouldGetMemberByIdWithSuccess() {
            //Arrange
            var input = new Member(
                    1L,
                    "tiago@gmail.com",
                    "Tiago",
                    LocalDate.of(2000,04,21),
                    "11955702763",
                    "33523145632",
                    Plan.ANNUAL,
                    null
            );

            doReturn(Optional.of(input)).when(memberRepository).findById(idMemberArgumentCaptor.capture());

            //Act
            var output = memberQueryService.getMemberById(input.getId());

            //Assert
            assertNotNull(output);
            assertEquals(input.getId(), idMemberArgumentCaptor.getValue());
        }

        @Test
        @DisplayName("Should get member by id with failed")
        void shouldGetMemberByIdWithFailed() {
            //Arrange
            var id = 1L;
            doThrow(new ProfileNotFoundException()).when(memberRepository).findById(idMemberArgumentCaptor.capture());

            //Act & Assert
            assertThrows(ProfileNotFoundException.class, () -> memberQueryService.getMemberById(id));

        }
    }

    @Nested
    class findAllMembers {

        @Test
        @DisplayName("Should return a page with members")
        void shouldReturnPageWithMembers() {
            //Arrange
            var input = new Member(
                    1L,
                    "tiago@gmail.com",
                    "Tiago",
                    LocalDate.of(2000,04,21),
                    "11955702763",
                    "33523145632",
                    Plan.ANNUAL,
                    null
            );

            Pageable pageable = PageRequest.of(0, 10);
            Page<Member> page = new PageImpl<>(List.of(input), pageable, 1);

            doReturn(page).when(memberRepository).findAll(pageable);

            //Act
            var output = memberQueryService.findAllMembers(pageable);

            //Assert
            assertNotNull(output);
            assertEquals(1, output.getTotalElements());
            assertEquals(input.getEmail(), output.getContent().get(0).getEmail());
        }
    }




}