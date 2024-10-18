package com.project.demo_mq.config;

import com.project.demo_mq.model.Course;
import com.project.demo_mq.model.Registration;
import com.project.demo_mq.model.Student;
import com.project.demo_mq.publisher.RegistrationPublisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@ConditionalOnProperty(name = "data.loader.enabled", havingValue = "true")
public class DataLoader implements CommandLineRunner {

    private final RegistrationPublisher publisher;

    public DataLoader(RegistrationPublisher publisher) {
        this.publisher = publisher;
        System.out.println("DataLoader instance created");
    }

    @Override
    public void run(String... args) throws Exception {
        int numberOfRegistrations = 100; // Giả lập 100 sinh viên đăng ký
        ExecutorService executor = Executors.newFixedThreadPool(30); // Sử dụng 10 thread

        for (int i = 1; i <= numberOfRegistrations; i++) {
            final int index = i;
            executor.submit(() -> {
                Registration registration = new Registration(
                        new Student("S" + String.format("%03d", index), "Sinh Viên " + index),
                        new Course("C003", "Học máy")
                );
                publisher.publishRegistration(registration);
                System.out.println("Đã gửi đăng ký: " + registration);
            });
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
