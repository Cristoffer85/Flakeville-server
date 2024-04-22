package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.controller;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/getOneEmployee/{username}")
    public Employee getOneEmployee(@PathVariable String username) {
        return roleService.getOneEmployee(username);
    }

    @PutMapping("/updateEmployee/{username}")
    public Employee updateEmployee(@PathVariable String username, @RequestBody Employee employeeDetails) {
        return roleService.updateEmployee(username, employeeDetails);
    }
}
