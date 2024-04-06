package cristoffer85.exam.snofjallbywithptbackend.controller;

import cristoffer85.exam.snofjallbywithptbackend.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getEmployeeByUsername/{username}")
    public Employee getEmployeeByUsername(@PathVariable String username) {
        return employeeService.getEmployeeByUsername(username);
    }

    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/updateEmployee/{username}")
    public Employee updateEmployee(@PathVariable String username, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(username, employeeDetails);
    }

    @DeleteMapping("/deleteEmployee/{username}")
    public void deleteEmployee(@PathVariable String username) {
        employeeService.deleteEmployee(username);
    }
}
