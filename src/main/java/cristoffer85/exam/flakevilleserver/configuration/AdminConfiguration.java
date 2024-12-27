package cristoffer85.exam.flakevilleserver.configuration;

import cristoffer85.exam.flakevilleserver.MAINAPP.model.Admin;
import cristoffer85.exam.flakevilleserver.MAINAPP.model.Role;
import cristoffer85.exam.flakevilleserver.MAINAPP.repository.AdminRepository;
import cristoffer85.exam.flakevilleserver.MAINAPP.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class AdminConfiguration {               // Class to mainly set up the admins = below in the method initializeAdminUser()

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;
    @Value("${ADMIN_PASSWORD2}")
    private String adminPassword2;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;

    @PostConstruct
    public void init() {
        initializeRoles();
        initializeAdminUser();
    }

    private void initializeRoles() {
        if (roleRepository.findByAuthority("ADMIN").isEmpty()) {
            roleRepository.save(new Role("ADMIN"));
        }
        if (roleRepository.findByAuthority("EMPLOYEE").isEmpty()) {
            roleRepository.save(new Role("EMPLOYEE"));
        }
        if (roleRepository.findByAuthority("USER").isEmpty()) {
            roleRepository.save(new Role("USER"));
        }
    }

    private void initializeAdminUser() {
        Role adminRole = roleRepository.findByAuthority("ADMIN")
                .orElseThrow(() -> new RuntimeException("ADMIN role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
    
        // -- Create or update Admin 1 --
        Admin admin = adminRepository.findByUsername("admin")
                .orElse(new Admin());
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode(adminPassword));
        admin.setName("Pelle Larsson");
        admin.setPosition("Uber boss no 1");
        admin.setAuthorities(roles);
        adminRepository.save(admin);
    
        // -- Create or update Admin 2 --
        Admin admin2 = adminRepository.findByUsername("admin2")
                .orElse(new Admin());
        admin2.setUsername("admin2");
        admin2.setPassword(passwordEncoder.encode(adminPassword2));
        admin2.setName("Lisa Larsson");
        admin2.setPosition("Uber boss no 2 (no less than Uber Boss 1)");
        admin2.setAuthorities(roles);
        adminRepository.save(admin2);
        //----------------------------------
        
    }
}