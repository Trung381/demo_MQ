package com.project.demo_mq.subscriber;

import com.project.demo_mq.config.RabbitMQConfig;
import com.project.demo_mq.model.Registration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RegistrationSubscriber {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleRegistration(Registration registration) {
        // Xử lý đăng ký môn học, ví dụ: lưu vào cơ sở dữ liệu
        System.out.println("Đã nhận đăng ký: " + registration);
        // TODO: Thêm logic xử lý đăng ký, như lưu vào DB
    }
}

