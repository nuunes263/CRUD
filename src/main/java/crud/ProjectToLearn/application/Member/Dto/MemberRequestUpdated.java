package crud.ProjectToLearn.application.Member.Dto;

import crud.ProjectToLearn.domain.Enums.Plan;

public record MemberRequestUpdated(
        String email,
        Plan plan,
        String phone
) {
}
