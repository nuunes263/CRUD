package crud.ProjectToLearn.application.Teacher;

import crud.ProjectToLearn.application.Helper.MemberMapper;
import crud.ProjectToLearn.domain.Entity.Teacher;
import crud.ProjectToLearn.domain.Exceptions.TypeException.ProfileNotFoundException;
import crud.ProjectToLearn.domain.Exceptions.TypeException.TeacherAlreadyLinkedException;
import crud.ProjectToLearn.infrastructure.repository.MemberRepository;
import crud.ProjectToLearn.infrastructure.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final MemberRepository memberRepository;
    private final MemberMapper mapper;

    public Page<Teacher> findAllTeachers(Pageable pagination){
        return teacherRepository.findAll(pagination);
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(ProfileNotFoundException::new);
    }

    public Teacher SaveTeacher(TeacherRequest teacherRequest) {
        var teacherNew = new Teacher(teacherRequest);
        return teacherRepository.save(teacherNew);
    }

    public void DeleteById(Long id) {
        var teacherEntity = teacherRepository.findById(id)
                .orElseThrow(ProfileNotFoundException::new);

        teacherRepository.deleteById(id);
    }

    public Teacher UpdateTeacher(Long id, TeacherRequest teacherRequest){
        var teacherEntity = teacherRepository.findById(id)
                .orElseThrow(ProfileNotFoundException::new);

        mapper.updateTeacherFromDto(teacherRequest, teacherEntity);
        teacherRepository.saveAndFlush(teacherEntity);

        return new Teacher(
                teacherEntity.getId(),
                teacherEntity.getName(),
                teacherEntity.getGraduation(),
                teacherEntity.getCref(),
                teacherEntity.getMembers());
    }

    public void AddTeacherToMember(Long id_teacher, Long id_member){
        var teacher = teacherRepository.findById(id_teacher)
                .orElseThrow(ProfileNotFoundException::new);
        var member = memberRepository.findById(id_member)
                .orElseThrow(ProfileNotFoundException::new);

        if (member.getTeacher() != null) {
            throw new TeacherAlreadyLinkedException();
        }

        member.setTeacher(teacher);
        memberRepository.save(member);
    }
}
