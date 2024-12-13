package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.dto.RegistrationDTO;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.dto.UserUpdateDTO;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.User;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.EmployeeRepository;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getOneUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User registerUser(RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    public User updateUser(String username, UserUpdateDTO userUpdateDTO) {
        User existingUser = userRepository.findByUsername(username).orElse(null);
        if (existingUser != null) {
            existingUser.setBirthday(userUpdateDTO.getBirthday());
            existingUser.setAddress(userUpdateDTO.getAddress());
            existingUser.setTelephone(userUpdateDTO.getTelephone());
            existingUser.setEmail(userUpdateDTO.getEmail());

            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteOneUser(String username) {
        userRepository.deleteByUsername(username);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getOneEmployee(String username) {
        return employeeRepository.findByUsername(username).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return authenticationService.registerEmployee(
                employee.getUsername(),
                employee.getPassword(),
                employee.getName(),
                employee.getPosition()
        );
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
        Employee employee = employeeRepository.findByUsername(username).orElse(null);
        if (employee != null) {
            employeeRepository.delete(employee);
        }
    }
}