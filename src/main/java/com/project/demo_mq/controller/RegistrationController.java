package com.project.demo_mq.controller;

import com.project.demo_mq.model.Course;
import com.project.demo_mq.model.Registration;
import com.project.demo_mq.model.Student;
import com.project.demo_mq.publisher.RegistrationPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
    private final RegistrationPublisher publisher;

    public RegistrationController(RegistrationPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping
    public ResponseEntity<String> registerCourse(@RequestBody Registration registration) {
        publisher.publishRegistration(registration);
        return ResponseEntity.ok("Yêu cầu đăng ký đã được gửi");
    }

    // Endpoint mới để giả lập nhiều sinh viên đăng ký
    @PostMapping("/bulk")
    public ResponseEntity<String> bulkRegister(@RequestParam int numberOfRegistrations) {
        List<Registration> registrations = new ArrayList<>();

        for (int i = 1; i <= numberOfRegistrations; i++) {
            Registration registration = new Registration(
                    new Student("S" + String.format("%03d", i), "Sinh Viên " + i),
                    new Course("C001", "Lập trình Java")
            );
            registrations.add(registration);
        }

        // Gửi từng đăng ký thông qua publisher
        registrations.forEach(publisher::publishRegistration);

        return ResponseEntity.ok("Đã gửi " + numberOfRegistrations + " yêu cầu đăng ký");
    }
}