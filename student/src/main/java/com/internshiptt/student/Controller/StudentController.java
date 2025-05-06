package com.internshiptt.student.Controller;

import com.internshiptt.client.*;  // Import FeignClient for Application Service
import com.internshiptt.client.InternshipClient;  // Import FeignClient for Internship Service

import com.internshiptt.entity.Models.*;
import com.internshiptt.student.Service.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.internshiptt.entity.Models.Application.Status.Pending;


@RestController
@RequestMapping("/student")

public class StudentController {

    private final StudentService studentService;
    private final com.internshiptt.client.AppClient appClient;  // Feign client for ApplicationService
    private final InternshipClient internshipClient;  // Feign client for InternshipService

    // Inject FeignClients
    public StudentController(StudentService studentService, com.internshiptt.client.AppClient appClient, InternshipClient internshipClient) {
        this.studentService = studentService;
        this.appClient = appClient;
        this.internshipClient = internshipClient;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Student st) {
        studentService.registerAsStudent(st);
        return ResponseEntity.ok("Registered Successfully");
    }

    @PutMapping("/updateprofile/{id}")
    public ResponseEntity<String> update_profile(@PathVariable int id, @RequestBody Student student) {
        String result = studentService.updateProfile(student, id);
        if (result.equals("Profile updated successfully!")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        String result = studentService.login(email, password);

        return switch (result) {
            case "Login Successful" -> ResponseEntity.ok("Login Successful");
            case "Password Incorrect" -> ResponseEntity.status(401).body("Password Incorrect");
            case "User Not Found" -> ResponseEntity.status(404).body("User Not Found");
            default -> ResponseEntity.status(500).body("Unknown Error");
        };
    }
    @PostMapping("/submitapplication")
    public ResponseEntity<String> submit_app(@RequestBody Application app) {
        app.setStatus(Pending);
        // Directly call ApplicationService using FeignClient
        appClient.submitApplication(app);
        return ResponseEntity.ok("Application submitted successfully!");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }
    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Internship> getAllInternships() {
        // Directly call InternshipService using FeignClient
        return internshipClient.getAllInternships();
    }
}

