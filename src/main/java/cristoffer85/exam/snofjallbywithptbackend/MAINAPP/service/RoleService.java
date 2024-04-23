package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.AdminRepository;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.EmployeeRepository;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Override class for library UserDetailsService that lets you log in as Admin, Employee or User. Its needed for the security config, among others.

@Service
public class RoleService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities()))
                .orElseGet(() -> adminRepository.findByUsername(username)
                        .map(admin -> new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), admin.getAuthorities()))
                        .orElseGet(() -> employeeRepository.findByUsername(username)
                                .map(employee -> new org.springframework.security.core.userdetails.User(employee.getUsername(), employee.getPassword(), employee.getAuthorities()))
                                .orElseThrow(() -> new UsernameNotFoundException("Admin/Employee/User not found"))));
    }
}