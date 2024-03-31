package cristoffer85.exam.snofjallbywithptbackend.controller;

import cristoffer85.exam.snofjallbywithptbackend.model.User;
import cristoffer85.exam.snofjallbywithptbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String helloAdminController() {
        return "Admin level access";
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getOneUser/{username}")
    public User getOneUser(@PathVariable String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        user.setMaxHoursSlept(user.getMaxHoursSlept());
        user.setWeekDay(user.getWeekDay());
        return userRepository.save(user);
    }

    @PutMapping("/updateUser/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User user) {
        User existingUser = userRepository.findByUsername(username).orElse(null);
        if (existingUser != null) {
            // Update user fields as needed
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setAuthorities(user.getAuthorities());
            existingUser.setMaxHoursSlept(user.getMaxHoursSlept());
            existingUser.setWeekDay(user.getWeekDay());
            return userRepository.save(existingUser);
        }
        return null; // User not found
    }

    @DeleteMapping("/deleteOneUser/{username}")
    public void deleteOneUser(@PathVariable String username) {
        userRepository.deleteByUsername(username);
    }
}
