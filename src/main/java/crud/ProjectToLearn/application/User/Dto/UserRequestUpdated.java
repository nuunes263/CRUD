package crud.ProjectToLearn.application.User.Dto;

import crud.ProjectToLearn.domain.entity.User;
import crud.ProjectToLearn.domain.enums.Plan;

public record UserRequestUpdated(
        String email,
        Plan plan,
        String phone
) {
    public UserRequestUpdated(User user){
        this(user.getEmail(), user.getPlan(), user.getPhone());
    }
}
