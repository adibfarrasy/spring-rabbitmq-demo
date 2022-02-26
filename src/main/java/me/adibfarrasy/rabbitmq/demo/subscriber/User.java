package me.adibfarrasy.rabbitmq.demo.subscriber;

import me.adibfarrasy.rabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static me.adibfarrasy.rabbitmq.demo.config.MessagingConfig.QUEUE;

@Component
public class User {

    @RabbitListener(queues = QUEUE)
    public void consumeMessageFromQueue(String orderStatus) {
        System.out.println("Message received from queue: " + orderStatus);
    }
}
