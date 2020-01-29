package aleusers.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Value("${primaryQueue.name}")
    private String primaryQueueName;

    @Value("${deadLetterQueue.name}")
    private String deadLetterQueueName;

    @Bean
    public Queue mainQueue() {
        Map<String, Object> args = new HashMap<>();
        // The default exchange
        args.put("x-dead-letter-exchange", "");
        // Route to the incoming queue when the TTL occurs
        args.put("x-dead-letter-routing-key", deadLetterQueueName);
        return new Queue(primaryQueueName, true, false, false, args);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(deadLetterQueueName);
    }

}

