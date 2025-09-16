package crud.ProjectToLearn.application.User;

import crud.ProjectToLearn.application.Helper.UserMapper;
import crud.ProjectToLearn.application.User.Dto.UserRequest;
import crud.ProjectToLearn.application.User.Dto.UserRequestUpdated;
import crud.ProjectToLearn.domain.entity.User;
import crud.ProjectToLearn.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public User saveUser(UserRequest userRequest) {
        var userNew = new User(userRequest);
        repository.save(userNew);

        return userNew;
    }

    public Page<User> findAllUser(Pageable pagination){
        return repository.findAll(pagination);
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public User updateUser(Long id, UserRequestUpdated userRequestUpdated) {
        var userEntity = getUserById(id);

        mapper.updateUserFromDto(userRequestUpdated, userEntity);
        repository.saveAndFlush(userEntity);

        return userEntity;
    }
}
