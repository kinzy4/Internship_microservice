package com.internshiptt.internship.Service;

import com.internshiptt.client.HrClient;  // FeignClient to communicate with HR service
import com.internshiptt.client.*;  // FeignClient to communicate with Application service
import com.internshiptt.entity.Models.Internship;
import com.internshiptt.internship.Repo.InternshipRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InternshipService {

    private final InternshipRepo internRepo;
    private final HrClient hrClient;  // FeignClient for HR service
    private final com.internshiptt.client.AppClient applicationClient;  // FeignClient for Application service

    public InternshipService(InternshipRepo internRepo, HrClient hrClient, com.internshiptt.client.AppClient applicationClient) {
        this.internRepo = internRepo;
        this.hrClient = hrClient;
        this.applicationClient = applicationClient;
    }
    public Internship getInternshipById(int id) {
        Optional<Internship> optionalInternship = internRepo.findById(id);

        return optionalInternship.get();
    }
    // Method to add an internship
    @Transactional
    public String addInternship(Internship internship, Integer hrId) {
        try {
            // Fetch HR details via FeignClient (you only need HR ID here)

            if (hrClient.isValidHr(hrId)) {
                internship.setHrId(hrId);  // Store only HR ID as a reference
                internRepo.save(internship);
                internRepo.flush(); // force Hibernate to send to DB immediately
                return "Internship added Successfully";
            } else {
                return "Invalid HR ID!";
            }
        } catch (Exception e) {
            return "Error adding internship: " + e.getMessage();
        }
    }

    // Method to update an internship
    public String updateInternship(Internship internship, int id) {
        Optional<Internship> existingInternshipOpt = internRepo.findById(id);
        if (existingInternshipOpt.isPresent()) {
            Internship existingInternship = existingInternshipOpt.get();

            // Update fields
            existingInternship.setTitle(internship.getTitle());
            existingInternship.setRequirements(internship.getRequirements());
            existingInternship.setType(internship.getType());
            existingInternship.setLocation(internship.getLocation());
            existingInternship.setPeriod(internship.getPeriod());
            existingInternship.setHrId(internship.getHrId());  // Update HR ID reference
            internRepo.save(existingInternship);

            return "Internship updated successfully!";
        } else {
            return "Internship with ID " + id + " not found!";
        }
    }

    // Method to get all internships
    public List<Internship> getAllInternships() {
        return internRepo.findAll();
    }
}
