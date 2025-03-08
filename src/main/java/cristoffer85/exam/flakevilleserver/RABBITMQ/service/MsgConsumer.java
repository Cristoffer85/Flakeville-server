package cristoffer85.exam.flakevilleserver.RABBITMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MsgConsumer {
    private final Map<String, List<String>> messages = new ConcurrentHashMap<>();
    private final Map<String, Integer> unreadMessagesCount = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> unreadMessagesSenders = new ConcurrentHashMap<>();

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

        String queueKey = sender.compareTo(receiver) < 0 
            ? sender + "_" + receiver 
            : receiver + "_" + sender;

        messages.computeIfAbsent(queueKey, k -> new ArrayList<>())
                .add(sender + ": " + msgContent);

        unreadMessagesCount.merge(receiver, 1, Integer::sum);
        unreadMessagesSenders.computeIfAbsent(receiver, k -> ConcurrentHashMap.newKeySet()).add(sender);

        System.out.println("Received message for " + queueKey + ": " + sender + ": " + msgContent);
    }

    public List<String> getMessages(String queueKey) {
        return new ArrayList<>(messages.getOrDefault(queueKey, new ArrayList<>()));
    }

    public int getUnreadMessagesCount(String username) {
        return unreadMessagesCount.getOrDefault(username, 0);
    }

    public Set<String> getUnreadMessagesSenders(String username) {
        return unreadMessagesSenders.getOrDefault(username, ConcurrentHashMap.newKeySet());
    }

    public void markMessagesAsRead(String username) {
        unreadMessagesCount.remove(username);
        unreadMessagesSenders.remove(username);
    }
}