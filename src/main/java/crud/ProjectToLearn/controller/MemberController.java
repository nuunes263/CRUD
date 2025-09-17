package crud.ProjectToLearn.controller;

import crud.ProjectToLearn.application.Member.Dto.MemberRequest;
import crud.ProjectToLearn.application.Member.Dto.MemberRequestUpdated;
import crud.ProjectToLearn.application.Member.MemberService;
import crud.ProjectToLearn.domain.Entity.Member;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService MemberService;

    @Transactional
    @PostMapping
    public ResponseEntity saveMember(@RequestBody @Valid MemberRequest memberRequest, UriComponentsBuilder uriComponentsBuilder){
        var Member = MemberService.saveMember(memberRequest);
        var uri = uriComponentsBuilder.path("/member/{id}").buildAndExpand(Member.getId()).toUri();

        return ResponseEntity.created(uri).body(new MemberRequest(Member));
    }

    @GetMapping
    public ResponseEntity<Page<Member>> findAllMember(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        return ResponseEntity.ok(MemberService.findAllMember(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> findById(@PathVariable Long id){
        return ResponseEntity.ok(MemberService.getMemberById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMemberByEmail(@PathVariable Long id){
        MemberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable Long id, @RequestBody MemberRequestUpdated memberRequestUpdated){
        return ResponseEntity.ok(MemberService.updateMember(id, memberRequestUpdated));
    }
}
