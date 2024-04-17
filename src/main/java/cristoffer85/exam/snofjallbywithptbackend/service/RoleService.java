package cristoffer85.exam.snofjallbywithptbackend.service;

import cristoffer85.exam.snofjallbywithptbackend.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.model.User;
import cristoffer85.exam.snofjallbywithptbackend.repository.AdminRepository;
import cristoffer85.exam.snofjallbywithptbackend.repository.EmployeeRepository;
import cristoffer85.exam.snofjallbywithptbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements UserDetailsService {


    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    public User getOneUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities()))
                .orElseGet(() -> adminRepository.findByUsername(username)
                        .map(admin -> new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), admin.getAuthorities()))
                        .orElseGet(() -> employeeRepository.findByUsername(username)
                                .map(employee -> new org.springframework.security.core.userdetails.User(employee.getUsername(), employee.getPassword(), employee.getAuthorities()))
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"))));
    }
}