package cristoffer85.exam.flakevilleserver.MAINAPP.service;

import cristoffer85.exam.flakevilleserver.MAINAPP.dto.RegistrationDTO;
import cristoffer85.exam.flakevilleserver.MAINAPP.dto.UserUpdateDTO;
import cristoffer85.exam.flakevilleserver.MAINAPP.model.Employee;
import cristoffer85.exam.flakevilleserver.MAINAPP.model.User;
import cristoffer85.exam.flakevilleserver.MAINAPP.repository.EmployeeRepository;
import cristoffer85.exam.flakevilleserver.MAINAPP.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        User user1 = new User();
        User user2 = new User();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> result = adminService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getOneUser() {
        User user = new User();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        User result = adminService.getOneUser("username");

        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    void registerUser() {
        User user = new User();
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setUsername("username");
        registrationDTO.setPassword("password");
        when(authenticationService.registerUser(anyString(), anyString())).thenReturn(user);

        User result = adminService.registerUser(registrationDTO);

        assertEquals(user, result);
        verify(authenticationService, times(1)).registerUser(anyString(), anyString());
    }

    @Test
    void updateUser() {
        User user = new User();
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = adminService.updateUser("username", userUpdateDTO);

        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsername(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void deleteOneUser() {
        doNothing().when(userRepository).deleteByUsername(anyString());

        adminService.deleteOneUser("username");

        verify(userRepository, times(1)).deleteByUsername(anyString());
    }

    @Test
    void getAllEmployees() {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> result = adminService.getAllEmployees();

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void getOneEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.findByUsername(anyString())).thenReturn(Optional.of(employee));

        Employee result = adminService.getOneEmployee("username");

        assertEquals(employee, result);
        verify(employeeRepository, times(1)).findByUsername(anyString());
    }

    @Test
    void createEmployee() {
        Employee employee = new Employee();
        employee.setUsername("username");
        employee.setPassword("password");
        employee.setName("name");
        employee.setPosition("position");

        when(authenticationService.registerEmployee(anyString(), anyString(), anyString(), anyString())).thenReturn(employee);

        Employee result = adminService.createEmployee(employee);

        assertNotNull(result, "The created employee should not be null");
        assertEquals(employee, result);
        verify(authenticationService, times(1)).registerEmployee(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void updateEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.findByUsername(anyString())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = adminService.updateEmployee("username", employee);

        assertEquals(employee, result);
        verify(employeeRepository, times(1)).findByUsername(anyString());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void deleteEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.findByUsername(anyString())).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(any(Employee.class));

        adminService.deleteEmployee("username");

        verify(employeeRepository, times(1)).findByUsername(anyString());
        verify(employeeRepository, times(1)).delete(any(Employee.class));
    }
}