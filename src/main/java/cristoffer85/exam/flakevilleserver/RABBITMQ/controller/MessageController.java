package cristoffer85.exam.flakevilleserver.RABBITMQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import cristoffer85.exam.flakevilleserver.RABBITMQ.dto.MsgDto;
import cristoffer85.exam.flakevilleserver.RABBITMQ.service.MsgProducer;

@RestController
@RequestMapping("/rabbitmq")
public class MessageController {

    @Autowired
    private MsgProducer msgProducer;

    @PostMapping("/publish")
    public String pushMsgIntoQueue(@RequestBody MsgDto msgDTO) {
        msgProducer.sendMsg(msgDTO.getMessage());
        return "Message sent: " + msgDTO.getMessage();
    }
}