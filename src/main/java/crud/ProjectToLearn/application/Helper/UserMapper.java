package crud.ProjectToLearn.application.Helper;

import crud.ProjectToLearn.application.User.Dto.UserRequestUpdated;
import crud.ProjectToLearn.domain.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserRequestUpdated dto, @MappingTarget User entity);
}