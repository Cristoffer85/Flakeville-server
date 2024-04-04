package cristoffer85.exam.snofjallbywithptbackend.service;

import cristoffer85.exam.snofjallbywithptbackend.model.User;
import cristoffer85.exam.snofjallbywithptbackend.repository.AdminRepository;
import cristoffer85.exam.snofjallbywithptbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    /*
    Class to be able to save admins in a separate collection for better storage-structure in MongoDB.
    With just switching to adminrepository from userrepository (which implements UserDetailsService already)
    the JSON/Jwt-response got removed due to Spring Frameworks UserDetailsService.
    Had to alter a bit. Doesnt affect security.

    */

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseGet(() -> adminRepository.findByUsername(username)
                        .map(admin -> (User) admin)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found")));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
