package crud.ProjectToLearn.application.Member.Query;

import crud.ProjectToLearn.application.Helper.MemberMapper;
import crud.ProjectToLearn.domain.Entity.Member;
import crud.ProjectToLearn.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryService{

    private final MemberRepository repository;
    private final MemberMapper mapper;

    public Page<Member> findAllMember(Pageable pagination){
        return repository.findAll(pagination);
    }

    public Member getMemberById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found."));
    }
}
