package cristoffer85.exam.snofjallbywithptbackend.service;

import cristoffer85.exam.snofjallbywithptbackend.repository.AdminRepository;
import cristoffer85.exam.snofjallbywithptbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities()))
                .orElseGet(() -> adminRepository.findByUsername(username)
                        .map(admin -> new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), admin.getAuthorities()))
                        .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
