package com.company.spring.student;

import com.company.spring.exceptions.ApiConflictException;
import com.company.spring.exceptions.ApiNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        //Check if student exists
        return studentRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Student with id " + id + " does not exist"));
    }

    public Student addStudent(Student student) {
        //Check if email is unique
        studentRepository.findStudentByEmail(student.getEmail()).ifPresent(s -> {
            log.error("Student with email {} already exists", student.getEmail());
            throw new ApiConflictException("Student with email " + student.getEmail() + " already exists");
        });

        log.info("Adding student: {}", student);
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Long id, String name, String email) {
        //Check if student exists
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            log.error("Student with id {} does not exist", id);
            return new ApiNotFoundException("Student with id " + id + " does not exist");
        });

        //Check if email is unique
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
        if (studentByEmail.isPresent() && !studentByEmail.get().getId().equals(id)) {
            log.error("Student with email {} already exists", email);
            throw new ApiConflictException("Student with email " + email + " already exists");
        }

        //Check if name not null or empty
        if (name != null && !name.isEmpty() && !name.equals(student.getName())) {
            student.setName(name);
        }

        //Check if email not null or empty
        if (email != null && !email.isEmpty() && !email.equals(student.getEmail())) {
            student.setEmail(email);
        }
        log.info("Updating student: {}", student);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        //Check if student exists
        if (!studentRepository.existsById(id)) {
            log.error("Student with id {} does not exist", id);
            throw new ApiNotFoundException("Student with id " + id + " does not exist");
        }
        log.info("Deleting student with id: {}", id);
        studentRepository.deleteById(id);
    }


}
