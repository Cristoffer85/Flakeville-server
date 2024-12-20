package cristoffer85.exam.flakevilleserver.MAINAPP.service;

import cristoffer85.exam.flakevilleserver.MAINAPP.dto.SendOnlyUserNameDTO;
import cristoffer85.exam.flakevilleserver.MAINAPP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private UserRepository userRepository;

    public List<SendOnlyUserNameDTO> getAllUserSummaries() {
        return userRepository.findAll().stream()
                .map(user -> new SendOnlyUserNameDTO(user.getUsername()))
                .collect(Collectors.toList());
    }
}