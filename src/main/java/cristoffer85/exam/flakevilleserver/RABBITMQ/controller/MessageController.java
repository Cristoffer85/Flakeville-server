package cristoffer85.exam.flakevilleserver.RABBITMQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import cristoffer85.exam.flakevilleserver.RABBITMQ.dto.MsgDto;
import cristoffer85.exam.flakevilleserver.RABBITMQ.service.MsgConsumer;
import cristoffer85.exam.flakevilleserver.RABBITMQ.service.MsgProducer;

import java.util.List;

@RestController
@RequestMapping("/rabbitmq")
public class MessageController {

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    private MsgConsumer msgConsumer;

    @PostMapping("/publish")
    public String pushMsgIntoQueue(@RequestBody MsgDto msgDTO) {
        msgProducer.sendMsg(msgDTO);
        return "Message sent from " + msgDTO.getSender() + " to " + msgDTO.getReceiver() + ": " + msgDTO.getMessage();
    }

    @GetMapping("/subscribe/{user1}/{user2}")
    public List<String> getMessagesBetweenUsers(@PathVariable String user1, @PathVariable String user2, @RequestHeader("X-Username") String loggedInUsername) {
        if (!user1.equals(loggedInUsername) && !user2.equals(loggedInUsername)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only view messages involving yourself.");
        }
        String queueName1 = "chat_" + user1 + "_to_" + user2 + "_Receiver";
        String queueName2 = "chat_" + user2 + "_to_" + user1 + "_Receiver";
        List<String> messages1 = msgConsumer.getMessages(queueName1);
        List<String> messages2 = msgConsumer.getMessages(queueName2);
        messages1.addAll(messages2);
        return messages1;
    }
}