package me.adibfarrasy.rabbitmq.demo.publisher;

import me.adibfarrasy.rabbitmq.demo.dto.Order;
import me.adibfarrasy.rabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static me.adibfarrasy.rabbitmq.demo.config.MessagingConfig.EXCHANGE;
import static me.adibfarrasy.rabbitmq.demo.config.MessagingConfig.ROUTING_KEY;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "PROCESSED", "order placed successfully in " + restaurantName);
        template.convertAndSend(EXCHANGE, ROUTING_KEY, orderStatus.toString());
        return "OK";
    }
}
