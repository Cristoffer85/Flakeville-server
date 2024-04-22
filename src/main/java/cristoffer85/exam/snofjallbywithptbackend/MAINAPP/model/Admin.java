package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model;

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
@Document(collection = "admins")
public class Admin implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;

    private String position;
    private String name;

    private Set<Role> authorities = new HashSet<>();

    public Admin() {
        super();
    }

    @JsonIgnore
    public Set<Role> getAuthorities() {
        return this.authorities;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
