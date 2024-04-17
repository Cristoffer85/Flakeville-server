package cristoffer85.exam.snofjallbywithptbackend.controller;

import cristoffer85.exam.snofjallbywithptbackend.DTO.UserUpdateDTO;
import cristoffer85.exam.snofjallbywithptbackend.model.User;
import cristoffer85.exam.snofjallbywithptbackend.repository.UserRepository;
import cristoffer85.exam.snofjallbywithptbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String helloUserController() {
        return "User access level";
    }

    @GetMapping("/getOneUser/{username}")
    public User getOneUser(@PathVariable String username) {
        return roleService.getOneUser(username);
    }

    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @RequestBody UserUpdateDTO userUpdateDto) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(userUpdateDto.getEmail());
        user.setBirthday(userUpdateDto.getBirthday());
        user.setAddress(userUpdateDto.getAddress());
        user.setTelephone(userUpdateDto.getTelephone());
        return userRepository.save(user);
    }
}
