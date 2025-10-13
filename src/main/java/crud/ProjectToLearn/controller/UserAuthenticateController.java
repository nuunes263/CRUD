package crud.ProjectToLearn.controller;

import crud.ProjectToLearn.application.User.Dto.UserCreated;
import crud.ProjectToLearn.application.User.Dto.UserData;
import crud.ProjectToLearn.domain.Entity.User;
import crud.ProjectToLearn.domain.Exceptions.TypeException.EmailAlreadyExistExeception;
import crud.ProjectToLearn.infrastructure.repository.UserRepository;
import crud.ProjectToLearn.infrastructure.security.Dto.DataTokenJWT;
import crud.ProjectToLearn.infrastructure.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class UserAuthenticateController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserData data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserData data){
        var user = repository.findByLogin(data.login());

        if (user != null) {
            throw new EmailAlreadyExistExeception();
        }

        var newUser = new User();
        newUser.setLogin(data.login());
        newUser.setPassword(passwordEncoder.encode(data.password()));
        repository.save(newUser);

        return ResponseEntity.ok(new UserCreated(data.login(), data.password()));
    }
}
