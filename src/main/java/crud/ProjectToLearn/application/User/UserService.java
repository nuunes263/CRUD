package crud.ProjectToLearn.application.User;

import crud.ProjectToLearn.domain.entity.User;
import crud.ProjectToLearn.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository repository;

    public void saveUser(UserDto userDto) {
        User userNew = new User(
                userDto.email(),
                userDto.name(),
                userDto.birthDate());

        repository.save(userNew);
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }

    public void deleteByEmail(String email) {
        repository.deleteByEmail(email);
    }

    public void updateUser(UserDto userDto) {
        User userEntity = getUserByEmail(userDto.email());
        User userUpdated = User.builder()
                .id(userEntity.getId())
                .email(userDto.email() != null ? userDto.email() : userEntity.getEmail())
                .name(userDto.name() != null ? userDto.name() : userEntity.getName())
                .birthDate(userDto.birthDate() != null ? userDto.birthDate() : userEntity.getBirthDate())
                .build();

        repository.saveAndFlush(userUpdated);
    }
}
