package com.internshiptt.client;

import com.internshiptt.entity.Models.Application;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "application",url = "http://localhost:8084") // This should match the service name in Eureka
public interface AppClient {

    @PostMapping("/applications/submit")  // Replace with the correct endpoint for submitting applications
    void submitApplication(@RequestBody Application app);
   @PutMapping("/applications/updatestatus/{id}")
    Application updateStatus(@PathVariable int id, @RequestBody Application.Status status);
}
