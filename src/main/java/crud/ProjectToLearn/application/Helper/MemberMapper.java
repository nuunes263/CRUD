package crud.ProjectToLearn.application.Helper;

import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequestUpdated;
import crud.ProjectToLearn.domain.Entity.Member;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMemberFromDto(MemberRequestUpdated dto, @MappingTarget Member entity);
}
