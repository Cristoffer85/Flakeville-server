package cristoffer85.exam.snofjallbywithptbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;

    private String password;

    private Set<Role> authorities = new HashSet<>();

    public User() {
        super();
    }

    @Override
    @JsonIgnore
    public Set<Role> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
