package cristoffer85.exam.flakevilleserver.MAINAPP.controller;

import cristoffer85.exam.flakevilleserver.MAINAPP.dto.UserUpdateDTO;
import cristoffer85.exam.flakevilleserver.MAINAPP.model.User;
import cristoffer85.exam.flakevilleserver.MAINAPP.service.UserService;
import cristoffer85.exam.flakevilleserver.STORE.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
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

    @PostMapping("addOrder/{username}/orders")
    public User addOrder(@PathVariable String username, @RequestBody Order order) {
        return userService.addOrder(username, order);
    }
}