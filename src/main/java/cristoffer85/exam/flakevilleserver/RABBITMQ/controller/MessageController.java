package cristoffer85.exam.flakevilleserver.RABBITMQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import cristoffer85.exam.flakevilleserver.RABBITMQ.messages.producer.MessageProducer;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;

    @GetMapping("/publish")
    public String pushMsgIntoQueue() {
        messageProducer.sendMsg();
        return "Success";
    }
}
