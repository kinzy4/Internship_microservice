package com.internshiptt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hr",url = "http://localhost:8083") // This should match the name registered in Eureka
public interface HrClient {

    @GetMapping("/hr/validate/{hrId}")
    boolean isValidHr(@PathVariable("hrId") Integer hrId);
}
