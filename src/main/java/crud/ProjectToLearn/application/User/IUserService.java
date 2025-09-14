package crud.ProjectToLearn.application.User;

import crud.ProjectToLearn.domain.entity.User;

public interface IUserService {

    void saveUser(UserDto userDto);
    User getUserByEmail(String email);
    void deleteByEmail(String email);
    void updateUser(UserDto user);
}
