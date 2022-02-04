package com.company.spring.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> studentRepository.saveAll(
                Arrays.asList(
                        new Student(
                                "John",
                                "john@gmail.com",
                                LocalDate.of(2000, 1, 1)
                        ),
                        new Student(
                                "Alex",
                                "alex@gmail.com",
                                LocalDate.of(1999, 11, 1)
                        ),
                        new Student(
                                "Bernard",
                                "bernard@gmail.com",
                                LocalDate.of(2000, 2, 11)
                        )
                )
        );
    }
}
