package cristoffer85.exam.snofjallbywithptbackend.DTO;

import cristoffer85.exam.snofjallbywithptbackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {     // Class that uses the
    private User user;
    private String jwt;
}
