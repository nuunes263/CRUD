package crud.ProjectToLearn.application.Member.Command;

import crud.ProjectToLearn.application.Helper.MemberMapper;
import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequest;
import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequestUpdated;
import crud.ProjectToLearn.domain.Entity.Member;
import crud.ProjectToLearn.domain.Exceptions.TypeException.EmailAlreadyExistExeception;
import crud.ProjectToLearn.domain.Exceptions.TypeException.MemberNotFoundException;
import crud.ProjectToLearn.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberRepository repository;
    private final MemberMapper mapper;

    public Member saveMember(MemberRequest memberRequest) {
        if (emailAlreadyExist(memberRequest)) {
            throw new EmailAlreadyExistExeception();
        }

        var memberNew = new Member(memberRequest);
        return repository.save(memberNew);
    }

    public void deleteById(Long id) {
        var memberEntity = repository.findById(id)
                .orElseThrow(MemberNotFoundException::new);

        repository.deleteById(id);
    }

    public Member updateMember(Long id, MemberRequestUpdated memberRequestUpdated){
        var memberEntity = repository.findById(id)
                .orElseThrow(MemberNotFoundException::new);

        mapper.updateMemberFromDto(memberRequestUpdated, memberEntity);
        repository.saveAndFlush(memberEntity);

        return new Member(
                memberEntity.getId(),
                memberEntity.getEmail(),
                memberEntity.getName(),
                memberEntity.getBirthDate(),
                memberEntity.getPhone(),
                memberEntity.getCpf(),
                memberEntity.getPlan()
        );
    }

    private boolean emailAlreadyExist(MemberRequest memberRequest) {
        return repository.findByEmail(memberRequest.email()).isPresent();
    }
}
