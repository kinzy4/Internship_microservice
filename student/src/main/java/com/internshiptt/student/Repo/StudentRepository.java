package com.internshiptt.student.Repo;

import com.internshiptt.entity.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findById(int id);
    Optional<Student> findByEmail(String email);
}
