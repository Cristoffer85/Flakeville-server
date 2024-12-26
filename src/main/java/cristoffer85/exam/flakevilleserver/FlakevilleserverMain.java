package cristoffer85.exam.flakevilleserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlakevilleserverMain {

    public static void main(String[] args) {
        System.out.println("RABBITMQ_HOST: " + System.getenv("RABBITMQ_HOST"));
        System.out.println("RABBITMQ_USERNAME: " + System.getenv("RABBITMQ_USERNAME"));
        System.out.println("RABBITMQ_PASSWORD: " + System.getenv("RABBITMQ_PASSWORD"));
        SpringApplication.run(FlakevilleserverMain.class, args);
    }
}