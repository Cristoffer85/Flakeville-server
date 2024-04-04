package cristoffer85.exam.snofjallbywithptbackend.controller;

import cristoffer85.exam.snofjallbywithptbackend.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.model.Role;
import cristoffer85.exam.snofjallbywithptbackend.model.User;
import cristoffer85.exam.snofjallbywithptbackend.repository.EmployeeRepository;
import cristoffer85.exam.snofjallbywithptbackend.repository.RoleRepository;
import cristoffer85.exam.snofjallbywithptbackend.repository.UserRepository;
import cristoffer85.exam.snofjallbywithptbackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private RoleRepository roleRepository;

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

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        user.setMaxHoursSlept(user.getMaxHoursSlept());
        return userRepository.save(user);
    }

    @PutMapping("/updateUser/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User user) {
        User existingUser = userRepository.findByUsername(username).orElse(null);
        if (existingUser != null) {

            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setAuthorities(user.getAuthorities());
            existingUser.setMaxHoursSlept(user.getMaxHoursSlept());

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

    @GetMapping("/getOneEmployee/{id}")
    public Employee getOneEmployee(@PathVariable String id) {
        return employeeRepository.findById(id).orElse(null);
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

    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable String id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {

            employee.setName(employeeDetails.getName());
            employee.setPosition(employeeDetails.getPosition());

            return employeeRepository.save(employee);
        }
        return null;
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeRepository.deleteById(id);
    }

}
