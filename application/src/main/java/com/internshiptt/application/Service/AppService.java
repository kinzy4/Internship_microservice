package com.internshiptt.application.Service;

import com.internshiptt.entity.Models.Application;
import com.internshiptt.application.Repo.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppService {

    private final ApplicationRepository applicationRepository;

    public AppService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void submitApplication(Application app) {
        applicationRepository.save(app);
    }

    public Application updateStatus(int applicationId, Application.Status newStatus) {
        Optional<Application> optionalApp = applicationRepository.findById(applicationId);
        if (optionalApp.isPresent()) {
            Application application = optionalApp.get();
            application.setStatus(newStatus);
            return applicationRepository.save(application);
        } else {
            throw new RuntimeException("Application with ID " + applicationId + " not found");
        }
    }
}
