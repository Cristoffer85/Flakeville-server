package cristoffer85.exam.flakevilleserver.RABBITMQ.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cristoffer85.exam.flakevilleserver.RABBITMQ.dto.MsgDto;

@Component
public class MsgProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    public void sendMsg(MsgDto msgDTO) {
        String queueName = "chat_" + msgDTO.getReceiver() + "_Receiver";
        Queue queue = new Queue(queueName, false);
        rabbitAdmin.declareQueue(queue); // Declare the queue
        rabbitTemplate.convertAndSend("chatQueue", msgDTO.getSender() + ":" + msgDTO.getMessage());
        System.out.println("Message sent to " + queue.getName() + ": " + msgDTO.getMessage());
    }
}