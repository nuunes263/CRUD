package crud.ProjectToLearn.controller;

import crud.ProjectToLearn.application.User.Dto.UserRequest;
import crud.ProjectToLearn.application.User.Dto.UserRequestUpdated;
import crud.ProjectToLearn.application.User.UserService;
import crud.ProjectToLearn.domain.entity.User;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Transactional
    @PostMapping
    public ResponseEntity saveUser(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder uriComponentsBuilder){
        var user = userService.saveUser(userRequest);
        var uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserRequest(user));
    }

    @GetMapping
    public ResponseEntity<Page<User>> findAllUser(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        return ResponseEntity.ok(userService.findAllUser(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserByEmail(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserRequestUpdated userRequestUpdated){
        return ResponseEntity.ok(userService.updateUser(id, userRequestUpdated));
    }
}
