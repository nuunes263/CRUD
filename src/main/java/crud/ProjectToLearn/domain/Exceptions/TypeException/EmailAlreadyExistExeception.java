package crud.ProjectToLearn.domain.Exceptions.TypeException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistExeception extends RuntimeException{

    public EmailAlreadyExistExeception(){
        super("E-mail já cadastrado.");
    }
    public EmailAlreadyExistExeception(String message){
        super(message);
    }

}
