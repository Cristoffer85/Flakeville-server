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
    private final Map<String, Map<String, Integer>> unreadMessagesCountBySender = new ConcurrentHashMap<>();

    @RabbitListener(queues = "chatQueue")
    public void receiveMessage(String message) {
        String[] parts = message.split(":");
        if (parts.length < 3) {
            System.out.println("Invalid message format: " + message);
            return;
        }
        String sender = parts[0].trim();
        String receiver = parts[1].trim();
        String msgContent = parts[2].trim();

        String queueKey = sender.compareTo(receiver) < 0 
            ? sender + "_" + receiver 
            : receiver + "_" + sender;

        messages.computeIfAbsent(queueKey, k -> new ArrayList<>())
                .add(sender + ": " + msgContent);

        // Update the per-sender unread count for the receiver:
        unreadMessagesCountBySender.computeIfAbsent(receiver, k -> new ConcurrentHashMap<>())
                .merge(sender, 1, Integer::sum);

        System.out.println("Received message for " + queueKey + ": " + sender + ": " + msgContent);
    }

    public List<String> getMessages(String queueKey) {
        return new ArrayList<>(messages.getOrDefault(queueKey, new ArrayList<>()));
    }

    // Sum all unread messages across all senders for this user.
    public int getUnreadMessagesCount(String username) {
        Map<String, Integer> map = unreadMessagesCountBySender.get(username);
        if (map == null) return 0;
        return map.values().stream().mapToInt(Integer::intValue).sum();
    }

    // Return the set of senders who have unread messages.
    public Set<String> getUnreadMessagesSenders(String username) {
        Map<String, Integer> map = unreadMessagesCountBySender.get(username);
        if (map == null) return ConcurrentHashMap.newKeySet();
        return map.keySet();
    }

    // Mark messages as read only for a specific conversation.
    public void markMessagesAsRead(String queueKey, String username) {
        // Extract the other participant from the queueKey.
        String[] parts = queueKey.split("_");
        if (parts.length != 2) return;
        String otherUser = parts[0].equals(username) ? parts[1] : parts[0];

        Map<String, Integer> map = unreadMessagesCountBySender.get(username);
        if (map != null) {
            map.remove(otherUser);
            if (map.isEmpty()) {
                unreadMessagesCountBySender.remove(username);
            }
        }
    }
}