package com.internshiptt.client;

import com.internshiptt.entity.Models.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "student",url = "http://localhost:8081")
public interface StudentClient {


        @GetMapping("/student/{id}")
        Student getStudentById(@PathVariable int id);

}
