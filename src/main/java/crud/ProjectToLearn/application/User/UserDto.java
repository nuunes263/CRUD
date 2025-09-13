package crud.ProjectToLearn.application.User;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserDto(

        @NotBlank
        @Email
        String email,

        @NotBlank
        String name,

        @NotNull
        @Past(message = "A data de nascimento deve ser no passado")
        LocalDate birthDate)
{
}
