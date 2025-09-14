package crud.ProjectToLearn.controller;

import crud.ProjectToLearn.application.User.UserDto;
import crud.ProjectToLearn.application.User.UserService;
import crud.ProjectToLearn.domain.entity.User;
import crud.ProjectToLearn.infrastructure.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository repository;

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody @Valid UserDto userDto){
        userService.saveUser(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<UserDto> findAllUser(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacao){
        return repository.findAll(paginacao)
                .map(UserDto::new);
    }

    @GetMapping("/by-email")
    public ResponseEntity<User> findByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserByEmail(@RequestBody String email){
        userService.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto){
        userService.updateUser(userDto);
        return ResponseEntity.ok().build();
    }
}
