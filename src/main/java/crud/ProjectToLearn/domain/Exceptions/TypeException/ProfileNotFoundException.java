package crud.ProjectToLearn.domain.Exceptions.TypeException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProfileNotFoundException extends RuntimeException{

    public ProfileNotFoundException(){ super("Aluno/Professor não encontrado."); }
    public ProfileNotFoundException(String message){super(message); }

}
