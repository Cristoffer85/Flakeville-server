package cristoffer85.exam.snofjallbywithptbackend.service;

import cristoffer85.exam.snofjallbywithptbackend.DTO.LoginResponseDTO;
import cristoffer85.exam.snofjallbywithptbackend.model.Employee;
import cristoffer85.exam.snofjallbywithptbackend.model.Role;
import cristoffer85.exam.snofjallbywithptbackend.model.User;
import cristoffer85.exam.snofjallbywithptbackend.repository.AdminRepository;
import cristoffer85.exam.snofjallbywithptbackend.repository.EmployeeRepository;
import cristoffer85.exam.snofjallbywithptbackend.repository.RoleRepository;
import cristoffer85.exam.snofjallbywithptbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
public class AuthenticationService {                // Class that handles Registration of new user, and login of a user (Authenticates that they are valid) Uses LoginResponseDTO among others

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

            // Generate a unique userId (you can use UUID for this)
            String userId = UUID.randomUUID().toString();
            newUser.setUserId(userId);

            return userRepository.save(newUser);

        } catch (DataIntegrityViolationException e) {
            // Catch the exception thrown when there's a unique constraint violation
            throw new RuntimeException("Username '" + username + "' already exists. Please choose a different username.");
        }
    }

    public Employee registerEmployee(String name, String position, String username, String password) {
        try {
            String encodedPassword = passwordEncoder.encode(password);
            Role employeeRole = roleRepository.findByAuthority("EMPLOYEE")
                    .orElseThrow(() -> new RuntimeException("EMPLOYEE role not found"));

            Set<Role> authorities = new HashSet<>();
            authorities.add(employeeRole);

            Employee newEmployee = new Employee();
            newEmployee.setName(name);
            newEmployee.setPosition(position);
            newEmployee.setUsername(username);
            newEmployee.setPassword(encodedPassword);
            newEmployee.setAuthorities(authorities);

            return employeeRepository.save(newEmployee);

        } catch (DataIntegrityViolationException e) {
            // Catch the exception thrown when there's a unique constraint violation
            throw new RuntimeException("Username '" + username + "' already exists. Please choose a different username.");
        }
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            String token = tokenService.generateJwt(auth);

            Object userOrAdmin;
            if (userRepository.findByUsername(username).isPresent()) {
                userOrAdmin = userRepository.findByUsername(username).get();
            } else if (adminRepository.findByUsername(username).isPresent()) {
                userOrAdmin = adminRepository.findByUsername(username).get();
            } else {
                userOrAdmin = null;
            }

            return new LoginResponseDTO(userOrAdmin, token);
        } catch (BadCredentialsException e) {
            // Handle bad credentials exception
            return new LoginResponseDTO(null, "Incorrect Credentials");
        } catch (AuthenticationException e) {
            // Handle other authentication exceptions
            return new LoginResponseDTO(null, "Authentication failed");
        }
    }
}
