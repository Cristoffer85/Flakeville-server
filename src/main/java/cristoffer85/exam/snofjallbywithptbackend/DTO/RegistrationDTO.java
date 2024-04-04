package cristoffer85.exam.snofjallbywithptbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {          // Class that Registers the username and password as Data-Transfer-Object, to a String
    private String username;
    private String password;

    @Override
    public String toString() {
        return "Registration info: Username: " + this.username + " Password: " + this.password;
    }
}
