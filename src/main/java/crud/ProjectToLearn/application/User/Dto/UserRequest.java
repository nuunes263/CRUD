package crud.ProjectToLearn.application.User.Dto;

import crud.ProjectToLearn.domain.entity.User;
import crud.ProjectToLearn.domain.enums.Plan;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UserRequest(
        @NotBlank
        String email,
        @NotBlank
        String name,
        LocalDate birthDate,
        String cpf,
        Plan plan,
        String phone)
{
    public UserRequest(User user){
        this(user.getEmail(), user.getName(), user.getBirthDate(), user.getCpf(), user.getPlan(), user.getPhone());
    }
}
