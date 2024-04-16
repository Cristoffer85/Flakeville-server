package cristoffer85.exam.snofjallbywithptbackend.controller;

import cristoffer85.exam.snofjallbywithptbackend.DTO.UserUpdateDTO;
import cristoffer85.exam.snofjallbywithptbackend.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.model.User;
import cristoffer85.exam.snofjallbywithptbackend.repository.EmployeeRepository;
import cristoffer85.exam.snofjallbywithptbackend.repository.UserRepository;
import cristoffer85.exam.snofjallbywithptbackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/")
    public String helloAdminController() {
        return "Admin level access";
    }

    // ################### Users ###################

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getOneUser/{username}")
    public User getOneUser(@PathVariable String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @PutMapping("/updateUser/{username}")
    public User updateUser(@PathVariable String username, @RequestBody UserUpdateDTO userUpdateDTO) {
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

    @DeleteMapping("/deleteOneUser/{username}")
    public void deleteOneUser(@PathVariable String username) {
        userRepository.deleteByUsername(username);
    }



    // ################### Employees ###################

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/getOneEmployee/{username}")
    public Employee getOneEmployee(@PathVariable String username) {
        return employeeRepository.findByUsername(username).orElse(null);
    }

    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return authenticationService.registerEmployee(
                employee.getName(),
                employee.getPosition(),
                employee.getUsername(),
                employee.getPassword()
        );
    }

    @PutMapping("/updateEmployee/{username}")
    public Employee updateEmployee(@PathVariable String username, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findByUsername(username).orElse(null);
        if (employee != null) {
            employee.setName(employeeDetails.getName());
            employee.setPosition(employeeDetails.getPosition());
            Employee updatedEmployee = employeeRepository.save(employee);
            return updatedEmployee;
        }
        return null;
    }

    @DeleteMapping("/deleteEmployee/{username}")
    public void deleteEmployee(@PathVariable String username) {
        Employee employee = employeeRepository.findByUsername(username).orElse(null);
        if (employee != null) {
            employeeRepository.delete(employee);
        }
    }
}
