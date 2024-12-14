package cristoffer85.exam.flakevilleserver.MAINAPP.service;

import cristoffer85.exam.flakevilleserver.MAINAPP.dto.LoginResponseDTO;
import cristoffer85.exam.flakevilleserver.MAINAPP.model.Admin;
import cristoffer85.exam.flakevilleserver.MAINAPP.model.Employee;
import cristoffer85.exam.flakevilleserver.MAINAPP.model.Role;
import cristoffer85.exam.flakevilleserver.MAINAPP.model.User;
import cristoffer85.exam.flakevilleserver.MAINAPP.repository.AdminRepository;
import cristoffer85.exam.flakevilleserver.MAINAPP.repository.EmployeeRepository;
import cristoffer85.exam.flakevilleserver.MAINAPP.repository.RoleRepository;
import cristoffer85.exam.flakevilleserver.MAINAPP.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class AuthenticationService {                // Class that handles Registration of new user and employee, and login (Authenticates that they are valid) Uses LoginResponseDTO among others

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmployeeRepository employeeRepository;

    public User registerUser(String username, String password) {
        try {
            String encodedPassword = passwordEncoder.encode(password);
            Role userRole = roleRepository.findByAuthority("USER")
                    .orElseThrow(() -> new RuntimeException("USER role not found"));

            Set<Role> authorities = new HashSet<>();
            authorities.add(userRole);

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(encodedPassword);
            newUser.setAuthorities(authorities);

            String userId = UUID.randomUUID().toString();
            newUser.setId(userId);

            return userRepository.save(newUser);

        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Username '" + username + "' already exists. Please choose a different username.");
        }
    }

    public Employee registerEmployee(String username, String password, String name, String position) {
        try {
            String encodedPassword = passwordEncoder.encode(password);
            Role employeeRole = roleRepository.findByAuthority("EMPLOYEE")
                    .orElseThrow(() -> new RuntimeException("EMPLOYEE role not found"));

            Set<Role> authorities = new HashSet<>();
            authorities.add(employeeRole);

            Employee newEmployee = new Employee();
            newEmployee.setUsername(username);
            newEmployee.setPassword(encodedPassword);
            newEmployee.setName(name);
            newEmployee.setPosition(position);
            newEmployee.setAuthorities(authorities);

            return employeeRepository.save(newEmployee);

        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Username '" + username + "' already exists. Please choose a different username.");
        }
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            String token = tokenService.generateJwt(auth);

            Object userOrAdminOrEmployee;
            Role role;
            if (userRepository.findByUsername(username).isPresent()) {
                userOrAdminOrEmployee = userRepository.findByUsername(username).get();
                role = ((User) userOrAdminOrEmployee).getAuthorities().iterator().next();
            } else if (adminRepository.findByUsername(username).isPresent()) {
                userOrAdminOrEmployee = adminRepository.findByUsername(username).get();
                role = ((Admin) userOrAdminOrEmployee).getAuthorities().iterator().next();
            } else if (employeeRepository.findByUsername(username).isPresent()) {
                userOrAdminOrEmployee = employeeRepository.findByUsername(username).get();
                role = ((Employee) userOrAdminOrEmployee).getAuthorities().iterator().next();
            } else {
                userOrAdminOrEmployee = null;
                role = null;
            }

            return new LoginResponseDTO(userOrAdminOrEmployee, token, role);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Credentials");
        } catch (AuthenticationException e) {
            throw new InternalAuthenticationServiceException("Authentication failed", e);
        }
    }
}