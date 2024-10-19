package com.project.demo_mq.publisher;

import com.project.demo_mq.config.RabbitMQConfig;
import com.project.demo_mq.model.Registration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegistrationPublisher {
    private final RabbitTemplate rabbitTemplate;

    public RegistrationPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishRegistration(Registration registration) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                "course.registration.student",
                registration
        );
        System.out.println("Đã gửi đăng ký: " + registration);
    }
}
