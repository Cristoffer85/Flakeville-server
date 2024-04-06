package cristoffer85.exam.snofjallbywithptbackend.configuration;

import cristoffer85.exam.snofjallbywithptbackend.model.Admin;
import cristoffer85.exam.snofjallbywithptbackend.model.Role;
import cristoffer85.exam.snofjallbywithptbackend.repository.AdminRepository;
import cristoffer85.exam.snofjallbywithptbackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class AdminSetup {               // Class to mainly set up the admins = below in the method initializeAdminUser()

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
        if (adminRepository.findByUsername("admin").isEmpty()) {
            Role adminRole = roleRepository.findByAuthority("ADMIN")
                    .orElseThrow(() -> new RuntimeException("ADMIN role not found"));
            Set<Role> roles = new HashSet<>();

            // Create a new Admin----------------
            roles.add(adminRole);
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("superadminpassword"));
            admin.setAuthorities(roles);
            admin.setPosition("Uber boss");
            adminRepository.save(admin);
            //----------------------------------

            // Create a new Admin 2 ----------------
            roles.add(adminRole);
            Admin admin2 = new Admin();
            admin2.setUsername("admin2");
            admin2.setPassword(passwordEncoder.encode("superadminpassword2"));
            admin2.setAuthorities(roles);
            admin2.setPosition("A little less Uber boss");
            adminRepository.save(admin2);
            //----------------------------------
        }
    }
}
