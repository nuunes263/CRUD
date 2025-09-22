package crud.ProjectToLearn.controller;

import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequest;
import crud.ProjectToLearn.application.Member.Command.Dto.MemberRequestUpdated;
import crud.ProjectToLearn.application.Member.Command.MemberCommandService;
import crud.ProjectToLearn.application.Member.Query.MemberQueryService;
import crud.ProjectToLearn.domain.Entity.Member;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberQueryService queryService;
    private final MemberCommandService commandService;

    @Transactional
    @PostMapping
    public ResponseEntity saveMember(@RequestBody @Valid MemberRequest memberRequest, UriComponentsBuilder uriComponentsBuilder){
        var member = commandService.saveMember(memberRequest);
        var uri = uriComponentsBuilder.path("/member/{id}").buildAndExpand(member.getId()).toUri();

        return ResponseEntity.created(uri).body(new MemberRequest(member));
    }

    @GetMapping
    public ResponseEntity<Page<Member>> findAllMember(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        return ResponseEntity.ok(queryService.findAllMember(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> findById(@PathVariable Long id){
        return ResponseEntity.ok(queryService.getMemberById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMemberByEmail(@PathVariable Long id){
        commandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable Long id, @RequestBody MemberRequestUpdated memberRequestUpdated){
        return ResponseEntity.ok(commandService.updateMember(id, memberRequestUpdated));
    }
}
