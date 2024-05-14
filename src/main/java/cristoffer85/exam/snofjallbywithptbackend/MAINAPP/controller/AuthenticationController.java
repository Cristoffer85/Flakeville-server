package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.controller;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.dto.LoginResponseDTO;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.dto.RegistrationDTO;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.User;
import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody RegistrationDTO body){
        try {
            LoginResponseDTO response = authenticationService.loginUser(body.getUsername(), body.getPassword());
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            // Return a 401 status code when the credentials are incorrect
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (AuthenticationException e) {
            // Return a 500 status code for other authentication errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
