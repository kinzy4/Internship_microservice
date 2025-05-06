package com.internshiptt.application.Controller;

import com.internshiptt.entity.Models.Application;
import com.internshiptt.application.Service.AppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
public class InternalApplicationController {

    private final AppService appService;



    public InternalApplicationController(AppService appService) {
        this.appService = appService;
    }

    // Endpoint to submit an application (internal for HR to use via AppClient)
    @PostMapping("/submit")
    public ResponseEntity<String> submitApplication(@RequestBody Application application) {
        try {
            appService.submitApplication(application);
            return ResponseEntity.ok("Application submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error submitting application: " + e.getMessage());
        }
    }

    // Endpoint to update the status of an application (internal for HR to use via AppClient)
    @PutMapping("/updatestatus/{id}")
    public ResponseEntity<Application> updateStatus(@PathVariable int id, @RequestBody Application.Status status) {
        try {
            Application updatedApplication = appService.updateStatus(id, status);
            return ResponseEntity.ok(updatedApplication);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
