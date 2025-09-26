package crud.ProjectToLearn.application.User.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserData(
        @NotBlank
        @Email
        String login,

        @NotBlank
        @Size(min = 5)
        String password) {
}
