package com.project.demo_mq.subscriber;

import com.project.demo_mq.config.RabbitMQConfig;
import com.project.demo_mq.model.Registration;
import com.project.demo_mq.service.RegistrationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RegistrationSubscriber {

    private final RegistrationService registrationService;

    public RegistrationSubscriber(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RabbitListener(
            queues = RabbitMQConfig.QUEUE_NAME,
            concurrency = "5-10",
            containerFactory = "rabbitListenerContainerFactory"
    )
    public void handleRegistration(Registration registration) {
        System.out.println("Đã nhận đăng ký: " + registration);
        try {
            registrationService.processRegistration(registration);
        } catch (Exception e) {
            System.err.println("Lỗi khi xử lý đăng ký: " + registration);
            e.printStackTrace();
            // Ném lại ngoại lệ để RabbitMQ biết cần retry hoặc đưa vào dead-letter queue
            throw e;
        }
    }
}
