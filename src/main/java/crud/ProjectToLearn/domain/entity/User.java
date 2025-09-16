package crud.ProjectToLearn.domain.entity;

import crud.ProjectToLearn.application.User.Dto.UserRequest;
import crud.ProjectToLearn.domain.enums.Plan;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;

    @Column(unique = true)
    public String email;
    private String name;
    private LocalDate birthDate;
    private String phone;

    @CPF
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Plan plan;

    public User(UserRequest userRequest) {
        this.name = userRequest.name();
        this.email = userRequest.email();
        this.birthDate = userRequest.birthDate();
        this.phone = userRequest.phone();
        this.cpf = userRequest.cpf();
        this.plan = userRequest.plan();
    }
}
