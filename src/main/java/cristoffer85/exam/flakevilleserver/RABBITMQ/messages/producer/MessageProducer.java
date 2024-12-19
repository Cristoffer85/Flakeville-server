package cristoffer85.exam.flakevilleserver.RABBITMQ.messages.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

        @Autowired
        private RabbitTemplate rabbitTemplate;

        public void sendMsg(){
            rabbitTemplate.convertAndSend("myQueue", "Hello, RabbitMQ!");
            System.out.println("Message sent.");
        }
}
