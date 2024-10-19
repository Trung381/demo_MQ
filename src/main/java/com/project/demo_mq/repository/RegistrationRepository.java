package com.project.demo_mq.repository;

import com.project.demo_mq.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    // Các phương thức truy vấn tùy chỉnh nếu cần
}
