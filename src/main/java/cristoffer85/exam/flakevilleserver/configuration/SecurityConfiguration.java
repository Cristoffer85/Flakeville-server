package cristoffer85.exam.flakevilleserver.configuration;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import cristoffer85.exam.flakevilleserver.MAINAPP.utils.RSAKeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {                    // Class responsible for the security of the application

    private final RSAKeyProperties keys;

    public SecurityConfiguration(RSAKeyProperties keys){
        this.keys = keys;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService detailsService){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(detailsService);
        daoProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())

                // Security Chain below structured, so that (Look a little closer in comments :)

                .authorizeHttpRequests(auth -> {

                    // --------------------------- PERMITTED FOR ALL --------------------------------
                    // = All users have access to /auth/** and /skiResort/** endpoints
                    auth.requestMatchers("/auth/**", "/skiResort/**", "/products/getAllProducts", "/products/getOneProduct/{id}", "/products/category/{category}", "/skilifts/getAllLifts").permitAll();

                    // --------------------------- ROLE ENDPOINTS --------------------------------
                    // = ADMIN is the only role with access to /admin/** endpoint
                    auth.requestMatchers("/admin/**").hasRole("ADMIN");

                    // = ADMIN and EMPLOYEE are only roles with access to /employee/** endpoint
                    auth.requestMatchers("/employee/**").hasAnyRole("ADMIN", "EMPLOYEE");

                    // = ADMIN, EMPLOYEE and USER are only roles with access to /user/** endpoint
                    auth.requestMatchers("/user/**").hasAnyRole("ADMIN", "EMPLOYEE", "USER");

                    // --------------------------- PRODUCT MANAGEMENT --------------------------------
                    // = ADMIN and EMPLOYEE are only roles with access to /products/** endpoint
                    auth.requestMatchers("products/createProduct", "/updateProduct/{id}", "/deleteProduct/{id}").hasAnyRole("ADMIN", "EMPLOYEE");

                    // --------------------------- SKILIFT MANAGEMENT --------------------------------
                    // = ADMIN and EMPLOYEE are only roles with access to /skilifts/** endpoint
                    auth.requestMatchers("/skilifts/startLift/", "/skilifts/stopLift/").hasAnyRole("ADMIN", "EMPLOYEE");

                    auth.anyRequest().authenticated();
                });

        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())));
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtConverter;
    }
}
