package cristoffer85.exam.snofjallbywithptbackend.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class UserUpdateDTO {
    private Date birthday;
    private String address;
    private String telephone;
    private String email;

    // Getters and setters...
}
