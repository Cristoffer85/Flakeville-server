package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.controller;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.dto.RegistrationDTO;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.dto.UserUpdateDTO;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.User;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/getOneUser/{username}")
    public User getOneUser(@PathVariable String username) {
        return adminService.getOneUser(username);
    }

    @PostMapping("/createUser")
    public User registerUser(@RequestBody RegistrationDTO body){
        return adminService.registerUser(body);
    }

    @PutMapping("/updateUser/{username}")
    public User updateUser(@PathVariable String username, @RequestBody UserUpdateDTO userUpdateDTO) {
        return adminService.updateUser(username, userUpdateDTO);
    }

    @DeleteMapping("/deleteOneUser/{username}")
    public void deleteOneUser(@PathVariable String username) {
        adminService.deleteOneUser(username);
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return adminService.getAllEmployees();
    }

    @GetMapping("/getOneEmployee/{username}")
    public Employee getOneEmployee(@PathVariable String username) {
        return adminService.getOneEmployee(username);
    }

    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return adminService.createEmployee(employee);
    }

    @PutMapping("/updateEmployee/{username}")
    public Employee updateEmployee(@PathVariable String username, @RequestBody Employee employeeDetails) {
        return adminService.updateEmployee(username, employeeDetails);
    }

    @DeleteMapping("/deleteEmployee/{username}")
    public void deleteEmployee(@PathVariable String username) {
        adminService.deleteEmployee(username);
    }
}