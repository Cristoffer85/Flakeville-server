package cristoffer85.exam.flakevilleserver;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlakevilleserverMain {

    public static void main(String[] args) {

        // Make spring boot application able to load .env file for local development
        Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
        //----------------------------------------------

        SpringApplication.run(FlakevilleserverMain.class, args);
    }
}