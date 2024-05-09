package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getOneEmployee() {
        Employee employee = new Employee();
        employee.setUsername("username");
        employee.setName("name");
        employee.setPosition("position");

        when(employeeRepository.findByUsername(anyString())).thenReturn(Optional.of(employee));

        Employee result = employeeService.getOneEmployee("username");

        assertEquals(employee, result);
        verify(employeeRepository, times(1)).findByUsername(anyString());
    }

    @Test
    void updateEmployee() {
        Employee existingEmployee = new Employee();
        existingEmployee.setUsername("username");
        existingEmployee.setName("name");
        existingEmployee.setPosition("position");

        Employee newDetails = new Employee();
        newDetails.setName("newName");
        newDetails.setPosition("newPosition");

        when(employeeRepository.findByUsername(anyString())).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(newDetails);

        Employee result = employeeService.updateEmployee("username", newDetails);

        assertEquals(newDetails, result);
        verify(employeeRepository, times(1)).findByUsername(anyString());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }
}