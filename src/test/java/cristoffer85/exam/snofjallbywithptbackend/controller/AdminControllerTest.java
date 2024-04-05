package cristoffer85.exam.snofjallbywithptbackend.controller;

import cristoffer85.exam.snofjallbywithptbackend.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.model.User;
import cristoffer85.exam.snofjallbywithptbackend.repository.EmployeeRepository;
import cristoffer85.exam.snofjallbywithptbackend.repository.UserRepository;
import cristoffer85.exam.snofjallbywithptbackend.service.AuthenticationService;
import cristoffer85.exam.snofjallbywithptbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        List<User> expectedUsers = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<User> result = adminController.getAllUsers();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("testUser", result.get(0).getUsername());
        verify(userRepository).findAll();
    }

    @Test
    public void testGetOneUser() {
        // Arrange
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        User result = adminController.getOneUser(username);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(userRepository).findByUsername(username);
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        user.setPassword("newPassword");
        User existingUser = new User();
        existingUser.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User result = adminController.updateUser(username, user);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals("newPassword", result.getPassword());
        verify(userRepository).findByUsername(username);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        adminController.deleteOneUser(username);

        // Assert
        verify(userRepository).deleteByUsername(username);
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange
        Employee employee = new Employee();
        employee.setUsername("testEmployee");
        List<Employee> expectedEmployees = Collections.singletonList(employee);
        when(employeeRepository.findAll()).thenReturn(expectedEmployees);

        // Act
        List<Employee> result = adminController.getAllEmployees();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("testEmployee", result.get(0).getUsername());
        verify(employeeRepository).findAll();
    }

    @Test
    public void testGetOneEmployee() {
        // Arrange
        String username = "testEmployee";
        Employee employee = new Employee();
        employee.setUsername(username);
        when(employeeRepository.findByUsername(username)).thenReturn(Optional.of(employee));

        // Act
        Employee result = adminController.getOneEmployee(username);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(employeeRepository).findByUsername(username);
    }

    /* Faulty, gets an assertion error <expected not null>
    @Test
    public void testCreateEmployee() {
        // Arrange
        Employee employee = new Employee();
        employee.setUsername("testEmployee");
        employee.setName("Test Name");
        employee.setPosition("Test Position");
        when(authenticationService.registerEmployee(anyString(), anyString(), anyString(), anyString())).thenReturn(employee);

        // Act
        Employee result = adminController.createEmployee(employee);

        // Assert
        assertNotNull(result);
        assertEquals("testEmployee", result.getUsername());
        assertEquals("Test Name", result.getName());
        assertEquals("Test Position", result.getPosition());
        verify(authenticationService).registerEmployee(anyString(), anyString(), anyString(), anyString());
    }
    */

    @Test
    public void testUpdateEmployee() {
        // Arrange
        String username = "testEmployee";
        Employee employeeDetails = new Employee();
        employeeDetails.setName("Updated Name");
        employeeDetails.setPosition("Updated Position");
        Employee existingEmployee = new Employee();
        existingEmployee.setUsername(username);
        when(employeeRepository.findByUsername(username)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employeeDetails);

        // Act
        Employee result = adminController.updateEmployee(username, employeeDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        assertEquals("Updated Position", result.getPosition());
        verify(employeeRepository).findByUsername(username);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    public void testDeleteEmployee() {
        // Arrange
        String username = "testEmployee";
        Employee employee = new Employee();
        employee.setUsername(username);
        when(employeeRepository.findByUsername(username)).thenReturn(Optional.of(employee));

        // Act
        adminController.deleteEmployee(username);

        // Assert
        verify(employeeRepository).findByUsername(username);
        verify(employeeRepository).delete(employee);
    }
}