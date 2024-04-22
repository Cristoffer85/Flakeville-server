package cristoffer85.exam.snofjallbywithptbackend.service;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TokenServiceTest {

    @Mock
    private JwtEncoder jwtEncoder;

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateJwt() {
        // Arrange
        String username = "testUser";
        String role = "USER";
        String token = "testToken";
        Authentication auth = new UsernamePasswordAuthenticationToken(
                new User(username, "", Collections.singleton(new SimpleGrantedAuthority(role))),
                null
        );
        Jwt jwt = Jwt.withTokenValue(token)
                .header("alg", "none")
                .claim("roles", role)
                .build();
        when(jwtEncoder.encode(any())).thenReturn(jwt);

        // Act
        String result = tokenService.generateJwt(auth);

        // Assert
        assertEquals(token, result);
        verify(jwtEncoder).encode(any());
    }
}