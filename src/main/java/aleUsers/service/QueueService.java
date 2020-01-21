package aleUsers.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Autowired
    protected AmqpTemplate template;


    public void addToQueue(String toQueue, Object message){
        template.convertAndSend(toQueue, message);
    }
}
