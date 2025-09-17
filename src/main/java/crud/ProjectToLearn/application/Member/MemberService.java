package crud.ProjectToLearn.application.Member;

import crud.ProjectToLearn.application.Helper.MemberMapper;
import crud.ProjectToLearn.application.Member.Dto.MemberRequest;
import crud.ProjectToLearn.application.Member.Dto.MemberRequestUpdated;
import crud.ProjectToLearn.domain.Entity.Member;
import crud.ProjectToLearn.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository repository;
    private final MemberMapper mapper;

    public Member saveMember(MemberRequest memberRequest) {
        var memberNew = new Member(memberRequest);
        repository.save(memberNew);

        return memberNew;
    }

    public Page<Member> findAllMember(Pageable pagination){
        return repository.findAll(pagination);
    }

    public Member getMemberById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found."));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Member updateMember(Long id, MemberRequestUpdated memberRequestUpdated) {
        var memberEntity = getMemberById(id);

        mapper.updateMemberFromDto(memberRequestUpdated, memberEntity);
        repository.saveAndFlush(memberEntity);

        return memberEntity;
    }
}
