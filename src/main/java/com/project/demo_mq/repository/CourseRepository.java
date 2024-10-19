package com.project.demo_mq.repository;

import com.project.demo_mq.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    // Các phương thức truy vấn tùy chỉnh nếu cần
}
