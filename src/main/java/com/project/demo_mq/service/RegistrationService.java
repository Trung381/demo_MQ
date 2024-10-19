package com.project.demo_mq.service;

import com.project.demo_mq.model.Course;
import com.project.demo_mq.model.Registration;
import com.project.demo_mq.model.Student;
import com.project.demo_mq.repository.CourseRepository;
import com.project.demo_mq.repository.RegistrationRepository;
import com.project.demo_mq.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public RegistrationService(RegistrationRepository registrationRepository,
                               StudentRepository studentRepository,
                               CourseRepository courseRepository) {
        this.registrationRepository = registrationRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void processRegistration(Registration registration) {
        // Kiểm tra hoặc tạo Student
        Optional<Student> studentOpt = studentRepository.findById(registration.getStudent().getStudentId());
        Student student = studentOpt.orElseGet(() -> studentRepository.save(registration.getStudent()));

        // Kiểm tra hoặc tạo Course
        Optional<Course> courseOpt = courseRepository.findById(registration.getCourse().getCourseId());
        Course course = courseOpt.orElseGet(() -> courseRepository.save(registration.getCourse()));

        // Thiết lập lại các tham chiếu để đảm bảo tính nhất quán
        registration.setStudent(student);
        registration.setCourse(course);

        // Lưu Registration
        registrationRepository.save(registration);

        // Thêm logic khác nếu cần
    }
}
