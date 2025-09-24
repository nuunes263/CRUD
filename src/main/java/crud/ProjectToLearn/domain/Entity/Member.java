package crud.ProjectToLearn.domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequest;
import crud.ProjectToLearn.domain.Enums.Plan;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "member")
@Table(name = "members")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @Column(unique = true)
    public String email;
    private String name;
    private LocalDate birthDate;
    private String phone;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = true)
    @JsonBackReference
    private Teacher teacher;


    public Member(MemberRequest memberRequest) {
        this.name = memberRequest.name();
        this.email = memberRequest.email();
        this.birthDate = memberRequest.birthDate();
        this.phone = memberRequest.phone();
        this.cpf = memberRequest.cpf();
        this.plan = memberRequest.plan();
        this.teacher = memberRequest.teacher();
    }
}
