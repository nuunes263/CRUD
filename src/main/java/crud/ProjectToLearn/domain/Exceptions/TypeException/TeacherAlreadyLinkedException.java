package crud.ProjectToLearn.domain.Exceptions.TypeException;

public class TeacherAlreadyLinkedException extends  RuntimeException {

    public TeacherAlreadyLinkedException(){ super("Aluno já está vinculado a um professor."); }
    public TeacherAlreadyLinkedException(String message){super(message); }

}
