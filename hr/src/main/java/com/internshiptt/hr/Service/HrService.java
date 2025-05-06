package com.internshiptt.hr.Service;

import com.internshiptt.entity.Models.Hr;
import com.internshiptt.hr.Repo.HrRepository;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HrService {

    private final HrRepository hrRepository;
    private final JavaMailSender mailSender;

    public HrService(HrRepository hrRepository, JavaMailSender mailSender) {
        this.hrRepository = hrRepository;
        this.mailSender = mailSender;
    }

    public void addHr(Hr hr) {
        hrRepository.save(hr);
    }

    public String hrlogin(String email, String rawPassword) {
        Hr hr = hrRepository.findByEmail(email);
        if (hr != null) {
            if (rawPassword.equals(hr.getPassword())) {
                return "Login Successful";
            } else {
                return "Password Incorrect";
            }
        } else {
            return "User Not Found";
        }
    }

    public String updateProfile(Hr updatedHr, int id) {
        Optional<Hr> optionalHr = hrRepository.findById(id);
        if (optionalHr.isPresent()) {
            Hr existingHr = optionalHr.get();

            existingHr.setName(updatedHr.getName());
            existingHr.setEmail(updatedHr.getEmail());
            existingHr.setPassword(updatedHr.getPassword());
            existingHr.setAddress(updatedHr.getAddress());
            existingHr.setAge(updatedHr.getAge());
            existingHr.setCompanyName(updatedHr.getCompanyName());
            existingHr.setPosition(updatedHr.getPosition());
            existingHr.setSalary(updatedHr.getSalary());

            hrRepository.save(existingHr);
            return "Profile updated successfully!";
        } else {
            return "HR not found!";
        }
    }

    public void sendAcceptanceEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mkinzy71@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Application Status: Accepted");
        message.setText("Congratulations! Your application for the internship  has been accepted.");

        try {
            mailSender.send(message);
            System.out.println("Email sent successfully!");
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    public void sendRejectionEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mkinzy71@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Application Status: Rejected");
        message.setText("We regret to inform you that your application for the internship  has been rejected. "
                + "Thank you for your interest and we encourage you to apply for future opportunities.");

        try {
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
    public boolean isValidHr(Integer hrId) {
        if (hrId == null ) {
            return false;
        }
        return hrRepository.existsByHrId(hrId);
    }
}
