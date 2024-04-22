package cristoffer85.exam.snofjallbywithptbackend.service;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.dto.LoginResponseDTO;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Role;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.User;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service.AuthenticationService;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service.TokenService;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.AdminRepository;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.EmployeeRepository;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.RoleRepository;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        String encodedPassword = "encodedTestPassword";
        Role userRole = new Role("USER");
        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setPassword(encodedPassword);
        expectedUser.setAuthorities(Collections.singleton(userRole));

        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(roleRepository.findByAuthority("USER")).thenReturn(Optional.of(userRole));
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        // Act
        User result = authenticationService.registerUser(username, password);

        // Assert
        assertEquals(expectedUser, result);
        verify(passwordEncoder).encode(password);
        verify(roleRepository).findByAuthority("USER");
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testRegisterEmployee() {
        // Arrange
        String name = "testEmployee";
        String position = "testPosition";
        String username = "testUsername";
        String password = "testPassword";
        String encodedPassword = "encodedTestPassword";
        Role employeeRole = new Role("EMPLOYEE");
        Employee expectedEmployee = new Employee();
        expectedEmployee.setName(name);
        expectedEmployee.setPosition(position);
        expectedEmployee.setUsername(username);
        expectedEmployee.setPassword(encodedPassword);
        expectedEmployee.setAuthorities(Collections.singleton(employeeRole));

        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(roleRepository.findByAuthority("EMPLOYEE")).thenReturn(Optional.of(employeeRole));
        when(employeeRepository.save(any(Employee.class))).thenReturn(expectedEmployee);

        // Act
        Employee result = authenticationService.registerEmployee(name, position, username, password);

        // Assert
        assertEquals(expectedEmployee, result);
        verify(passwordEncoder).encode(password);
        verify(roleRepository).findByAuthority("EMPLOYEE");
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    public void testLoginUser() {
        // Arrange
        String username = "testUsername";
        String password = "testPassword";
        String token = "testToken";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        LoginResponseDTO expectedResponse = new LoginResponseDTO(user, token);

        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(tokenService.generateJwt(auth)).thenReturn(token);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        LoginResponseDTO result = authenticationService.loginUser(username, password);

        // Assert
        assertEquals(expectedResponse, result);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenService).generateJwt(auth);
        verify(userRepository).findByUsername(username);
    }

}