package cristoffer85.exam.flakevilleserver.RABBITMQ.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String message) {
        rabbitTemplate.convertAndSend("myQueue", message);
        System.out.println("Message sent: " + message);
    }
}