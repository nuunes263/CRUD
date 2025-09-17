package crud.ProjectToLearn.domain.Entity;

import crud.ProjectToLearn.application.Member.Dto.MemberRequest;
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

    public Member(MemberRequest MemberRequest) {
        this.name = MemberRequest.name();
        this.email = MemberRequest.email();
        this.birthDate = MemberRequest.birthDate();
        this.phone = MemberRequest.phone();
        this.cpf = MemberRequest.cpf();
        this.plan = MemberRequest.plan();
    }
}
