package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email already exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudentById(Long id){
        boolean studentExists = studentRepository.existsById(id);

        if(!studentExists){
            throw new IllegalStateException("Cannot delete, user does not exists");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(
                        ()-> new IllegalStateException("Id not found")
                );
        if(name != null && !name.isEmpty() && !Objects.equals(student.getName(), name) ){
            student.setName(name);
        }
        if(email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email) ){
            student.setName(email);
        }
    }

}
