package crud.ProjectToLearn.domain.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import crud.ProjectToLearn.application.Teacher.TeacherRequest;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "teacher")
@Table(name = "teachers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String graduation;
    private String cref;

    @OneToMany(mappedBy = "teacher")
    @JsonManagedReference
    private List<Member> members = new ArrayList<>();


    public Teacher(TeacherRequest teacherRequest) {
        this.name = teacherRequest.name();
        this.graduation = teacherRequest.graduation();
        this.cref = teacherRequest.cref();
    }
}
