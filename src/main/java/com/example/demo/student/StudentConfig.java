package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student albert = new Student("Albert", "albert@mc2.com", LocalDate.of(1879, Month.MARCH, 15));
            Student tesla = new Student("Tesla", "tesla@eletic.com", LocalDate.of(1856, Month.JULY, 10));

            studentRepository.saveAll(List.of(albert, tesla));
        };
    }
}
