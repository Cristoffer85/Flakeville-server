package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.dto.LoginResponseDTO;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Role;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.User;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.EmployeeRepository;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.RoleRepository;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        Role role = new Role("USER");
        role.setAuthority("USER");
        Set<Role> authorities = new HashSet<>();
        authorities.add(role);
        user.setAuthorities(authorities);

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(roleRepository.findByAuthority(anyString())).thenReturn(java.util.Optional.of(role));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = authenticationService.registerUser("username", "password");

        assertEquals(user, result);
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(roleRepository, times(1)).findByAuthority(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerEmployee() {
        Employee employee = new Employee();
        employee.setUsername("username");
        employee.setPassword("password");
        employee.setName("name");
        employee.setPosition("position");
        Role role = new Role("EMPLOYEE");
        role.setAuthority("EMPLOYEE");
        Set<Role> authorities = new HashSet<>();
        authorities.add(role);
        employee.setAuthorities(authorities);

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(roleRepository.findByAuthority(anyString())).thenReturn(java.util.Optional.of(role));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = authenticationService.registerEmployee("username", "password", "name", "position");

        assertEquals(employee, result);
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(roleRepository, times(1)).findByAuthority(anyString());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void loginUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        Role role = new Role("USER");
        role.setAuthority("USER");
        Set<Role> authorities = new HashSet<>();
        authorities.add(role);
        user.setAuthorities(authorities);

        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.of(user));
        when(tokenService.generateJwt(any())).thenReturn("token");

        LoginResponseDTO result = authenticationService.loginUser("username", "password");

        assertEquals(user, result.getUser());
        assertEquals("token", result.getJwt());
        assertEquals(role, result.getRole());
        verify(userRepository, times(2)).findByUsername(anyString()); // Expect 2 invocations
        verify(tokenService, times(1)).generateJwt(any());
    }
}