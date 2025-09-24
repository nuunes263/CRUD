package crud.ProjectToLearn.application.Helper;

import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequestUpdated;
import crud.ProjectToLearn.application.Teacher.TeacherRequest;
import crud.ProjectToLearn.domain.Entity.Member;
import crud.ProjectToLearn.domain.Entity.Teacher;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMemberFromDto(MemberRequestUpdated dto, @MappingTarget Member entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeacherFromDto(TeacherRequest dto, @MappingTarget Teacher entity);
}
