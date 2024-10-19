package com.project.demo_mq.repository;

import com.project.demo_mq.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    // Các phương thức truy vấn tùy chỉnh nếu cần
}
