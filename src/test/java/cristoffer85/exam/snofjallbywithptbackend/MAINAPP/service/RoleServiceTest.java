package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Admin;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.User;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.AdminRepository;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.EmployeeRepository;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class RoleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_User() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setAuthorities(new HashSet<>());

        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.of(user));

        UserDetails result = roleService.loadUserByUsername("username");

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
    }

    @Test
    void loadUserByUsername_Admin() {
        Admin admin = new Admin();
        admin.setUsername("username");
        admin.setPassword("password");
        admin.setAuthorities(new HashSet<>());

        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.empty());
        when(adminRepository.findByUsername(anyString())).thenReturn(java.util.Optional.of(admin));

        UserDetails result = roleService.loadUserByUsername("username");

        assertEquals(admin.getUsername(), result.getUsername());
        assertEquals(admin.getPassword(), result.getPassword());
    }

    @Test
    void loadUserByUsername_Employee() {
        Employee employee = new Employee();
        employee.setUsername("username");
        employee.setPassword("password");
        employee.setAuthorities(new HashSet<>());

        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.empty());
        when(adminRepository.findByUsername(anyString())).thenReturn(java.util.Optional.empty());
        when(employeeRepository.findByUsername(anyString())).thenReturn(java.util.Optional.of(employee));

        UserDetails result = roleService.loadUserByUsername("username");

        assertEquals(employee.getUsername(), result.getUsername());
        assertEquals(employee.getPassword(), result.getPassword());
    }

    @Test
    void loadUserByUsername_NotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.empty());
        when(adminRepository.findByUsername(anyString())).thenReturn(java.util.Optional.empty());
        when(employeeRepository.findByUsername(anyString())).thenReturn(java.util.Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> roleService.loadUserByUsername("username"));
    }
}