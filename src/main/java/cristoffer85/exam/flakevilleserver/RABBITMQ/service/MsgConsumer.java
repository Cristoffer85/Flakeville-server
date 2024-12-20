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
        if (parts.length < 3) {
            System.out.println("Invalid message format: " + message);
            return;
        }
        String sender = parts[0];
        String receiver = parts[1];
        String msgContent = parts[2];
        String queueName = "chat_" + receiver + "_Receiver";
        messages.computeIfAbsent(queueName, k -> new ArrayList<>()).add(sender + ": " + msgContent);
        System.out.println("Received message for " + queueName + ": " + sender + ": " + msgContent);
    }

    public List<String> getMessages(String queueName) {
        return messages.getOrDefault(queueName, new ArrayList<>());
    }
}