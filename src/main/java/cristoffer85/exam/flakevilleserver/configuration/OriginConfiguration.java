package cristoffer85.exam.flakevilleserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OriginConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://flakeville.netlify.app/", "http://localhost:5173/", "http://localhost:5174/", "http://localhost:5175/", "http://localhost:5176/", "http://localhost:5177/", "http://localhost:5178/", "http://localhost:5179/", "http://localhost:5180/")                  // <---- Change * to the URL/domain you want to allow
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
