package crud.ProjectToLearn.application.Member.Command;

import crud.ProjectToLearn.application.Helper.MemberMapper;
import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequest;
import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequestUpdated;
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
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MemberCommandServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberCommandService memberCommandService;

    @Captor
    private ArgumentCaptor<Member> memberArgumentCaptor;

    @Captor
    private ArgumentCaptor<Long> idMemberArgumentCaptor;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class saveMember{
        @Test
        @DisplayName("should save a new member on DB")
        void SaveMemberCase1() {
            //Arrange
            var input = new MemberRequest(1L, "tiago@gmail.com", "Tiago", LocalDate.of(2000,04,21), "33523145632", Plan.ANNUAL, "11955761234", null);
            var member = new Member(input);

            doReturn(member).when(memberRepository).save(memberArgumentCaptor.capture());

            //Act
            var output = memberCommandService.SaveMember(input);

            //Assert
            assertNotNull(output);

            var memberCaptured = memberArgumentCaptor.getValue();

            verify(memberRepository, times(1)).save(memberCaptured);
            assertEquals(input.cpf(), memberCaptured.getCpf());
            assertEquals(input.phone(), memberCaptured.getPhone());
            assertEquals(input.email(), memberCaptured.getEmail());
            assertEquals(input.name(), memberCaptured.getName());
            assertEquals(input.plan(), memberCaptured.getPlan());
            assertEquals(input.birthDate(), memberCaptured.getBirthDate());
            assertEquals(input.teacher(), memberCaptured.getTeacher());

        }

        @Test
        @DisplayName("should throw exception when already exist an email equal.")
        void shouldThrowExceptionWhenAlreadyExistAnEmailEqual() {
            // Arrange
            var input = new MemberRequest(1L, "tiago@gmail.com", "Tiago",
                    LocalDate.of(2000, 04, 21), "33523145632",
                    Plan.ANNUAL, "11955761234", null);

            when(memberRepository.findByEmail(any()))
                    .thenReturn(Optional.of(new Member(input)));

            // Act & Assert
            assertThrows(EmailAlreadyExistExeception.class, () -> memberCommandService.SaveMember(input));
        }
    }

    @Nested
    class deleteById{

        @Test
        @DisplayName("Should delete member when id exists")
        void shouldDeleteMemberWhenIdExists() {
            // Arrange
            Long memberId = 1L;
            var member = new Member(
                    memberId,
                    "tiago@gmail.com",
                    "Tiago",
                    LocalDate.of(2000,04,21),
                    "11955702763",
                    "33523145632",
                    Plan.ANNUAL,
                    null
            );

            doReturn(Optional.of(member)).when(memberRepository).findById(memberId);

            // Act
            memberCommandService.DeleteById(memberId);

            // Assert
            verify(memberRepository, times(1)).findById(memberId);
            verify(memberRepository, times(1)).deleteById(memberId);
        }

        @Test
        @DisplayName("Should throw exception when id does not exist")
        void shouldThrowExceptionWhenIdDoesNotExist() {
            // Arrange
            Long memberId = 99L;
            doReturn(Optional.empty()).when(memberRepository).findById(memberId);

            // Act & Assert
            assertThrows(ProfileNotFoundException.class,
                    () -> memberCommandService.DeleteById(memberId));

            verify(memberRepository, times(1)).findById(memberId);
            verify(memberRepository, never()).deleteById(any());
        }
    }

    @Nested
    class updateMember {

        @Test
        @DisplayName("Should update member by id when member exists")
        void shouldUpdateMemberByIdWhenMemberExists() {
            // Arrange
            Long memberId = 1L;
            var existingMember = new Member(
                    memberId,
                    "oldemail@gmail.com",
                    "Tiago",
                    LocalDate.of(2000, 4, 21),
                    "11999999999",
                    "33523145632",
                    Plan.MONTHLY,
                    null
            );

            var updateRequest = new MemberRequestUpdated(
                    "newemail@gmail.com",
                    Plan.ANNUAL,
                    "11955702763"
            );

            // Simula a busca do membro existente
            doReturn(Optional.of(existingMember)).when(memberRepository).findById(memberId);

            // Act
            var updatedMember = memberCommandService.UpdateMember(memberId, updateRequest);

            // Assert
            // Verifica se o mapper foi chamado corretamente
            verify(memberMapper, times(1)).updateMemberFromDto(updateRequest, existingMember);
            // Verifica se o repository salvou e atualizou o membro
            verify(memberRepository, times(1)).saveAndFlush(existingMember);

            // Verifica se o retorno tem os dados corretos (copiando os campos do entity)
            assertEquals(existingMember.getId(), updatedMember.getId());
            assertEquals(existingMember.getEmail(), updatedMember.getEmail());
            assertEquals(existingMember.getName(), updatedMember.getName());
            assertEquals(existingMember.getBirthDate(), updatedMember.getBirthDate());
            assertEquals(existingMember.getPhone(), updatedMember.getPhone());
            assertEquals(existingMember.getCpf(), updatedMember.getCpf());
            assertEquals(existingMember.getPlan(), updatedMember.getPlan());
            assertEquals(existingMember.getTeacher(), updatedMember.getTeacher());
        }

        @Test
        @DisplayName("Should throw exception when member does not exist")
        void shouldThrowExceptionWhenMemberDoesNotExist() {
            // Arrange
            Long memberId = 99L;
            var updateRequest = new MemberRequestUpdated(
                    "notfound@gmail.com",
                    Plan.ANNUAL,
                    "11900000000"
            );

            // Simula que não encontrou o membro
            doReturn(Optional.empty()).when(memberRepository).findById(memberId);

            // Act & Assert
            assertThrows(ProfileNotFoundException.class,
                    () -> memberCommandService.UpdateMember(memberId, updateRequest));

            // Verifica se o mapper e saveAndFlush nunca foram chamados
            verify(memberMapper, never()).updateMemberFromDto(any(), any());
            verify(memberRepository, never()).saveAndFlush(any());
        }
    }

}