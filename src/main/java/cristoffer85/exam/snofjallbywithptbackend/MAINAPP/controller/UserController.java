package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.controller;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.dto.UserUpdateDTO;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.User;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getOneUser/{username}")
    public User getOneUser(@PathVariable String username) {
        return userService.getOneUser(username);
    }

    @PutMapping("/updateUser/{username}")
    public User updateUser(@PathVariable String username, @RequestBody UserUpdateDTO userUpdateDto) {
        return userService.updateUser(username, userUpdateDto);
    }
}