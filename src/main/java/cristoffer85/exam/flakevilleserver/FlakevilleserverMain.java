package cristoffer85.exam.flakevilleserver;

        //import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlakevilleserverMain {

    public static void main(String[] args) {
        /*Dotenv dotenv = Dotenv.load();
        System.setProperty("MONGODB_URI", dotenv.get("MONGODB_URI"));
        System.setProperty("RABBITMQ_HOST", dotenv.get("RABBITMQ_HOST"));
        System.setProperty("RABBITMQ_PORT", dotenv.get("RABBITMQ_PORT"));
        System.setProperty("RABBITMQ_USERNAME", dotenv.get("RABBITMQ_USERNAME"));
        System.setProperty("RABBITMQ_PASSWORD", dotenv.get("RABBITMQ_PASSWORD"));
        System.setProperty("ADMIN_PASSWORD", dotenv.get("ADMIN_PASSWORD"));
        System.setProperty("ADMIN_PASSWORD2", dotenv.get("ADMIN_PASSWORD2"));*/
        
        SpringApplication.run(FlakevilleserverMain.class, args);
    }
}