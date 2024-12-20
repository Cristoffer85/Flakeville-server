package cristoffer85.exam.flakevilleserver.MAINAPP.controller;

import cristoffer85.exam.flakevilleserver.MAINAPP.dto.SendOnlyUserNameDTO;
import cristoffer85.exam.flakevilleserver.MAINAPP.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/getAllUserNames")
    public List<SendOnlyUserNameDTO> getAllUserSummaries() {
        return chatService.getAllUserSummaries();
    }
}