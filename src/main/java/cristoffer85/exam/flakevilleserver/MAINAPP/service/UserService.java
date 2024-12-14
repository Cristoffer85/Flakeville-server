package cristoffer85.exam.flakevilleserver.MAINAPP.service;

import cristoffer85.exam.flakevilleserver.MAINAPP.dto.UserUpdateDTO;
import cristoffer85.exam.flakevilleserver.MAINAPP.model.User;
import cristoffer85.exam.flakevilleserver.MAINAPP.repository.UserRepository;
import cristoffer85.exam.flakevilleserver.STORE.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getOneUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String username, UserUpdateDTO userUpdateDto) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(userUpdateDto.getEmail());
        user.setBirthday(userUpdateDto.getBirthday());
        user.setAddress(userUpdateDto.getAddress());
        user.setTelephone(userUpdateDto.getTelephone());
        return userRepository.save(user);
    }

    public User addOrder(String username, Order order) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            user.getOrders().add(order);
            userRepository.save(user);
        }
        return user;
    }
}