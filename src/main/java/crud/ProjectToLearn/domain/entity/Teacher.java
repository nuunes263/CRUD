package crud.ProjectToLearn.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "teacher")
@Table(name = "teachers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String graduation;

}
