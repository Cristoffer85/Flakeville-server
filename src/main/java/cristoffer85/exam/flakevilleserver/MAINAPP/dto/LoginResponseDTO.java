package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.dto;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private Object user;
    private String jwt;
    private Role role;
}
