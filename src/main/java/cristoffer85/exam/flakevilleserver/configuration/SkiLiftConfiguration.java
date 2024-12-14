package cristoffer85.exam.flakevilleserver.configuration;

import cristoffer85.exam.flakevilleserver.SKILIFT.model.SkiLift;
import cristoffer85.exam.flakevilleserver.SKILIFT.repository.SkiLiftRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class SkiLiftConfiguration {

    @Bean
    CommandLineRunner initDatabase(SkiLiftRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                // Add your ski lifts here
                repository.save(new SkiLift(1L, "Lift 1", false, new ArrayList<>()));
                repository.save(new SkiLift(2L, "Lift 2", false, new ArrayList<>()));
            }
        };
    }
}
