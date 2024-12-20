package cristoffer85.exam.flakevilleserver.RABBITMQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/subscribe/{username}")
    public List<String> getMessages(@PathVariable String username) {
        String queueName = "chat_" + username + "_Receiver";
        return msgConsumer.getMessages(queueName);
    }
}