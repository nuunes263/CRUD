package crud.ProjectToLearn.application.Member.Command.Dto;

import crud.ProjectToLearn.domain.Entity.Member;
import crud.ProjectToLearn.domain.Enums.Plan;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record MemberRequest(
        Long id,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 8, max = 30, message = "O campo nome deve conter no mínimo de 8 a 30 letras")
        String name,

        @Past
        LocalDate birthDate,

        @NotBlank
        @CPF
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter somente números")
        String cpf,

        @NotNull(message = "O plano é obrigatório")
        Plan plan,

        @Pattern(regexp = "\\d{11}", message = "O telefone deve conter exatamente 11 dígitos, apenas números (DDD + número).")
        String phone
)
{
    public MemberRequest(Member member){
        this(member.getId(), member.getEmail(), member.getName(), member.getBirthDate(), member.getCpf(), member.getPlan(), member.getPhone());
    }
}
