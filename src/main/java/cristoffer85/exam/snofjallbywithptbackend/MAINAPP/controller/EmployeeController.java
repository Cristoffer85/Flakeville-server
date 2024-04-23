package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.controller;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getOneEmployee/{username}")
    public Employee getOneEmployee(@PathVariable String username) {
        return employeeService.getOneEmployee(username);
    }

    @PutMapping("/updateEmployee/{username}")
    public Employee updateEmployee(@PathVariable String username, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(username, employeeDetails);
    }
}