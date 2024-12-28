package cristoffer85.exam.flakevilleserver.configuration;

import org.springframework.context.annotation.Bean;
import io.github.cdimascio.dotenv.Dotenv;

public class DotenvConfiguration {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure().load();
    }
}
