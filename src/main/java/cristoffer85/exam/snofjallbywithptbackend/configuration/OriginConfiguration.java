package cristoffer85.exam.snofjallbywithptbackend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OriginConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")                  // <---- Change * to the URL/domain you want to allow
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
