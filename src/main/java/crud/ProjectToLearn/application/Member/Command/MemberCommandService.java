package crud.ProjectToLearn.application.Member.Command;

import crud.ProjectToLearn.application.Helper.MemberMapper;
import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequest;
import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequestUpdated;
import crud.ProjectToLearn.domain.Entity.Member;
import crud.ProjectToLearn.domain.Entity.User;
import crud.ProjectToLearn.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberRepository repository;
    private final MemberMapper mapper;

    public Member saveMember(MemberRequest memberRequest) {
        var memberNew = new Member(memberRequest);
        repository.save(memberNew);

        return memberNew;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Member updateMember(Long id, MemberRequestUpdated memberRequestUpdated) throws RuntimeException{
        var memberEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found."));

        mapper.updateMemberFromDto(memberRequestUpdated, memberEntity);
        repository.saveAndFlush(memberEntity);

        return new Member();
    }
}
