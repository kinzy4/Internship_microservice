package com.internshiptt.student.Service;

import com.internshiptt.entity.Models.Student;
import com.internshiptt.student.Repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepo;

    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student getStudentById(int id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);


            return optionalStudent.get();

    }
//-----------------(Register)-----------------------------------------------------
    public String registerAsStudent(Student st) {

        studentRepo.save(st);
        return "Student registered successfully!";
    }

 //------------------------------------------------------------------------------
 //-----------------(UpdateProfile)-----------------------------------------------------
    public String updateProfile(Student student, int id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);

        if (optionalStudent.isPresent()) {
            Student existing = optionalStudent.get();

            existing.setName(student.getName());
            existing.setEmail(student.getEmail());
            existing.setPassword(student.getPassword());
            existing.setAddress(student.getAddress());
            existing.setAge(student.getAge());
            existing.setType("student");
            existing.setUniversity(student.getUniversity());
            existing.setCollege(student.getCollege());
            existing.setMajor(student.getMajor());
            existing.setLevel(student.getLevel());
            existing.setGpa(student.getGpa());

            studentRepo.save(existing);
            return "Profile updated successfully!";
        } else {
            return "Student not found!";
        }
    }
    //------------------------------------------------------------------------------
    //-----------------(Login)-----------------------------------------------------
    public String login(String email, String rawPassword) {
        Optional<Student> optionalStudent = studentRepo.findByEmail(email);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            if (rawPassword.equals(student.getPassword())) {
                return "Login Successful";
            } else {
                return "Password Incorrect";
            }
        } else {
            return "Student Not Found";
        }
    }



}
