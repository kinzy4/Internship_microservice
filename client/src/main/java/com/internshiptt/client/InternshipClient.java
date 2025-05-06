package com.internshiptt.client;

import com.internshiptt.entity.Models.Internship;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name =  "INTERNSHIP",url = "http://localhost:8082") // must match Eureka service name
public interface InternshipClient {

    @GetMapping("/internal/internships")
    List<Internship> getAllInternships();

@PostMapping("/internal/add/{hrId}")  // Must match controller exactly
String addInternship(
        @RequestBody Internship internship,
        @PathVariable("hrId") String hrId  // Explicit variable name
);
    @PutMapping("/internal/update/{id}")
    String updateInternship(@RequestBody Internship internship, @PathVariable int id);

}
