package com.company.spring.student;

import com.company.spring.models.ApiResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return ApiResponseHandler.generateResponse(
                studentService.getStudents(),
                "Students retrieved successfully"
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        return ApiResponseHandler.generateResponse(
                studentService.getStudent(id),
                "Student retrieved successfully"
        );
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        return ApiResponseHandler.generateResponse(
                studentService.addStudent(student),
                "Student added successfully"
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ApiResponseHandler.generateResponse(
                null,
                "Student deleted successfully"
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestParam(required = false) String name,
                                           @RequestParam(required = false) String email) {
        return ApiResponseHandler.generateResponse(
                studentService.updateStudent(id, name, email),
                "Student updated successfully"
        );
    }

}
