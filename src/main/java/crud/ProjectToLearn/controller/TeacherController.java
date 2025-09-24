package crud.ProjectToLearn.controller;


import crud.ProjectToLearn.application.Teacher.TeacherRequest;
import crud.ProjectToLearn.application.Teacher.TeacherService;
import crud.ProjectToLearn.domain.Entity.Teacher;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("teacher")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class TeacherController {

    private final TeacherService teacherService;

    @Transactional
    @PostMapping
    public ResponseEntity saveTeacher(@RequestBody @Valid TeacherRequest teacherRequest, UriComponentsBuilder uriComponentsBuilder){
        var teacher = teacherService.SaveTeacher(teacherRequest);
        var uri = uriComponentsBuilder.path("/teacher/{id}").buildAndExpand(teacher.getId()).toUri();

        return ResponseEntity.created(uri).body(new TeacherRequest(teacher));
    }

    @GetMapping
    public ResponseEntity<Page<Teacher>> findAllTeacher(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        return ResponseEntity.ok(teacherService.findAllTeachers(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable Long id){
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacherById(@PathVariable Long id){
        teacherService.DeleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable Long id, @RequestBody TeacherRequest teacherRequest){
        return ResponseEntity.ok(teacherService.UpdateTeacher(id, teacherRequest));
    }

    @Transactional
    @PatchMapping("/{id_teacher}/{id_member}")
    public ResponseEntity addTeacher(@PathVariable Long id_teacher, @PathVariable Long id_member){
        teacherService.AddTeacherToMember(id_teacher, id_member);
        return ResponseEntity.ok().build();
    }


}
