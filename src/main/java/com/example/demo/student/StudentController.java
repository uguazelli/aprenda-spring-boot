package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @PutMapping(path = "/id")
    public void updateStudent(@PathVariable("id") Long id,
                              @RequestParam("name") String name,
                              @RequestParam("email") String email) {

        studentService.updateStudent(id, name, email);

    }

    @DeleteMapping(path = "/{id}")
    public void deleteStudentById(@PathVariable("id") Long id){
        studentService.deleteStudentById(id);
    }

}
