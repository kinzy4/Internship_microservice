package com.internshiptt.internship.Controller;

import com.internshiptt.entity.Models.Internship;
import com.internshiptt.internship.Service.InternshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal") // prevent public exposure
public class InternalInternshipController {

    private final InternshipService internshipService;

    public InternalInternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @PostMapping("/add/{hrId}")
    public String addInternship(@RequestBody Internship internship, @PathVariable Integer hrId) {
        return internshipService.addInternship(internship, hrId);
    }

    @GetMapping("/getinternbyid/{id}")
    public ResponseEntity<Internship> getInternshipInternal(@PathVariable int id) {
        Internship internship = internshipService.getInternshipById(id);
        return ResponseEntity.ok()
                .header("X-Internal-Call", "true")
                .body(internship);
    }
    @PutMapping("/update/{id}")
    public String updateInternship(@RequestBody Internship internship, @PathVariable int id) {
        return internshipService.updateInternship(internship, id);
    }

    @GetMapping("/internships")
    public List<Internship> getAllInternships() {
        return internshipService.getAllInternships();
    }
}

