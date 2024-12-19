package cristoffer85.exam.flakevilleserver.RABBITMQ.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", false);
    }
}
