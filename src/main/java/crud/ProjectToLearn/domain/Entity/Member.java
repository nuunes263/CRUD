package crud.ProjectToLearn.domain.Entity;

import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequest;
import crud.ProjectToLearn.domain.Enums.Plan;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Member")
@Table(name = "members")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
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

    public Member(MemberRequest memberRequest) {
        this.name = memberRequest.name();
        this.email = memberRequest.email();
        this.birthDate = memberRequest.birthDate();
        this.phone = memberRequest.phone();
        this.cpf = memberRequest.cpf();
        this.plan = memberRequest.plan();
    }
}
