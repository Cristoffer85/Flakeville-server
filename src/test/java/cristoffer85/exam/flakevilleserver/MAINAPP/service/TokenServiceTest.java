package cristoffer85.exam.flakevilleserver.MAINAPP.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private JwtEncoder jwtEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateJwt() {
        User principal = new User("username", "password", Collections.singletonList(new SimpleGrantedAuthority("USER")));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("roles", "USER")
                .build();

        when(jwtEncoder.encode(any())).thenReturn(jwt);

        String result = tokenService.generateJwt(authentication);

        assertEquals("token", result);
    }
}