package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.dto.UserUpdateDTO;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.User;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.UserRepository;
import cristoffer85.exam.snofjallbywithptbackend.STORE.model.Order;
import cristoffer85.exam.snofjallbywithptbackend.STORE.model.Product;
import cristoffer85.exam.snofjallbywithptbackend.STORE.model.ProductQuantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getOneUser() {
        User user = new User();
        user.setUsername("username");

        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.of(user));

        User result = userService.getOneUser("username");

        assertEquals(user, result);
    }

    @Test
    void updateUser() throws ParseException {
        User user = new User();
        user.setUsername("username");

        UserUpdateDTO userUpdateDto = new UserUpdateDTO();
        userUpdateDto.setEmail("newEmail");

        // Convert String to Date
        String dateStr = "1990-01-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dateStr);
        userUpdateDto.setBirthday(date);

        userUpdateDto.setAddress("newAddress");
        userUpdateDto.setTelephone("newTelephone");

        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.updateUser("username", userUpdateDto);

        assertEquals(userUpdateDto.getEmail(), result.getEmail());
        assertEquals(userUpdateDto.getBirthday(), result.getBirthday());
        assertEquals(userUpdateDto.getAddress(), result.getAddress());
        assertEquals(userUpdateDto.getTelephone(), result.getTelephone());
    }


    @Test
    void addOrder() {
        User user = new User();
        user.setUsername("username");
        user.setOrders(new ArrayList<>());

        String orderId = "order1";
        List<ProductQuantity> products = new ArrayList<>();
        products.add(new ProductQuantity(new Product(), 2));
        products.add(new ProductQuantity(new Product(), 3));

        Order order = new Order(orderId, products);

        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.addOrder("username", order);

        assertEquals(1, result.getOrders().size());
        assertEquals(order, result.getOrders().get(0));
    }
}