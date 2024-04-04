package cristoffer85.exam.snofjallbywithptbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "employees")
public class Employee implements UserDetails {

    @Id
    private String id;
    private String name;
    private String position;

    @Indexed(unique = true)
    private String username;
    private String password;

    private Set<Role> authorities = new HashSet<>();

    // other fields as needed

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

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", username='" + username + '\'' +
                ", authorities=" + getAuthorityStrings() +
                '}';
    }

    public String getAuthorityStrings() {
        StringBuilder result = new StringBuilder("[");
        for (Role role : authorities) {
            result.append(role.getAuthority()).append(", ");
        }
        if (!authorities.isEmpty()) {
            result.delete(result.length() - 2, result.length());
        }
        result.append("]");
        return result.toString();
    }

}
