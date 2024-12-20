package cristoffer85.exam.flakevilleserver.RABBITMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MsgConsumer {

    @RabbitListener(queues = "chat_Pelle72_Receiver")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}