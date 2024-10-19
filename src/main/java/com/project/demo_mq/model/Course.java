package com.project.demo_mq.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "course_id", nullable = false, unique = true)
    private String courseId;

    @Column(name = "course_name", nullable = false)
    private String courseName;
}
