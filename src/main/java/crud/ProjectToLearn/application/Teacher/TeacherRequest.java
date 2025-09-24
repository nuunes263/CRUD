package crud.ProjectToLearn.application.Teacher;

import crud.ProjectToLearn.domain.Entity.Teacher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TeacherRequest (

        @NotBlank
        @Size(min = 8, max = 30, message = "O campo nome deve conter no mínimo de 8 a 30 letras")
        String name,
        String graduation,

        @Pattern(
                regexp = "^(?i)(?:CREF[\\-\\s]?)?\\d{6}-[A-Z]+\\/[A-Z]{2}$",
                message = "CREF inválido. Formato esperado: CREF 002887-G/SC"
        )
        String cref){

    public TeacherRequest(Teacher teacher){
        this(teacher.getName(), teacher.getGraduation(), teacher.getCref());
    }
}
