package cristoffer85.exam.flakevilleserver.RABBITMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MsgConsumer {

    private final Map<String, List<String>> messages = new HashMap<>();

    @RabbitListener(queues = "chatQueue")
    public void receiveMessage(String message) {
        String[] parts = message.split(":");
        if (parts.length < 2) {
            System.out.println("Invalid message format: " + message);
            return;
        }
        String queueName = "chat_" + parts[0] + "_Receiver";
        messages.computeIfAbsent(queueName, k -> new ArrayList<>()).add(message);
        System.out.println("Received message from " + queueName + ": " + message);
    }

    public List<String> getMessages(String queueName) {
        return messages.getOrDefault(queueName, new ArrayList<>());
    }

    public void clearMessages(String queueName) {
        messages.remove(queueName);
    }
}