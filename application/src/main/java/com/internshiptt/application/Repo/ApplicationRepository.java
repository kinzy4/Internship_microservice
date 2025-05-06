package com.internshiptt.application.Repo;

import com.internshiptt.entity.Models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {



    List<Application> findByStudentId(int studentId);

    List<Application> findByInternshipId(int internshipId);
}
