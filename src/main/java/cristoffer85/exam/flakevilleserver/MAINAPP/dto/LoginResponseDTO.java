package cristoffer85.exam.flakevilleserver.MAINAPP.dto;

import cristoffer85.exam.flakevilleserver.MAINAPP.model.Role;
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
