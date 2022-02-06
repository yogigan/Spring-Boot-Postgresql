package com.company.spring.student;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, Faker faker) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            students.add(new Student(faker.name().firstName(),
                    faker.internet().emailAddress(),
                    faker.date().birthday().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()));
        }
        return args -> studentRepository.saveAll(students);
    }
}
