package crud.ProjectToLearn.application.User;

import crud.ProjectToLearn.domain.entity.User;

import java.util.List;

public interface IUserService {

    void saveUser(UserDto userDto);
    User getUserByEmail(String email);
    void deleteByEmail(String email);
    void updateUser(UserDto user);
    List<User> findAllUser();
}
