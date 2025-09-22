package crud.ProjectToLearn.domain.Exceptions;

import crud.ProjectToLearn.domain.Exceptions.TypeException.EmailAlreadyExistExeception;
import crud.ProjectToLearn.domain.Exceptions.TypeException.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleMemberNotFound(MemberNotFoundException ex){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(EmailAlreadyExistExeception.class)
    public ResponseEntity<RestErrorMessage> handleEmailAlreadyExist(EmailAlreadyExistExeception ex){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity HandlerError400(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorValidation::new).toList());
    }

    public record DataErrorValidation(String field, String message){
        public DataErrorValidation(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
