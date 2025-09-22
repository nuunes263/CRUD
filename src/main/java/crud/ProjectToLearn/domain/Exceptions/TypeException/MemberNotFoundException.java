package crud.ProjectToLearn.domain.Exceptions.TypeException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends RuntimeException{

    public MemberNotFoundException(){ super("Aluno não encontrado."); }
    public MemberNotFoundException(String message){super(message); }

}
