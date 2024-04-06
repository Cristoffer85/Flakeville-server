package cristoffer85.exam.snofjallbywithptbackend.service;

import cristoffer85.exam.snofjallbywithptbackend.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(String username, Employee employeeDetails) {
        Employee employee = employeeRepository.findByUsername(username).orElse(null);
        if (employee != null) {
            employee.setName(employeeDetails.getName());
            employee.setPosition(employeeDetails.getPosition());
            return employeeRepository.save(employee);
        }
        return null;
    }

    public void deleteEmployee(String username) {
        employeeRepository.deleteByUsername(username);
    }
}
