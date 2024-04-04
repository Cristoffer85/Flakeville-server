package cristoffer85.exam.snofjallbywithptbackend.controller;

import cristoffer85.exam.snofjallbywithptbackend.DTO.LoginResponseDTO;
import cristoffer85.exam.snofjallbywithptbackend.DTO.RegistrationDTO;
import cristoffer85.exam.snofjallbywithptbackend.model.User;
import cristoffer85.exam.snofjallbywithptbackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}
