package crud.ProjectToLearn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    public String olaMundo(){
        return "HolloWorld";
    }
}
