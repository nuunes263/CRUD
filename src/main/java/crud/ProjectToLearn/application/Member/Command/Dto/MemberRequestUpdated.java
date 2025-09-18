package crud.ProjectToLearn.application.Member.Command.Dto;

import crud.ProjectToLearn.domain.Enums.Plan;

public record MemberRequestUpdated(
        String email,
        Plan plan,
        String phone
) {
}
