package cristoffer85.exam.flakevilleserver.MAINAPP.service;

import cristoffer85.exam.flakevilleserver.MAINAPP.model.Employee;
import cristoffer85.exam.flakevilleserver.MAINAPP.repository.EmployeeRepository;
import cristoffer85.exam.flakevilleserver.MAINAPP.model.Admin;
import cristoffer85.exam.flakevilleserver.MAINAPP.repository.AdminRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AdminRepository adminRepository;

    public Employee getOneEmployee(String username) {
        return employeeRepository.findByUsername(username).orElse(null);
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

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
