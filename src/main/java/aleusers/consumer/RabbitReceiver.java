package aleusers.consumer;

import aleusers.model.User;
import aleusers.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RabbitListener(queues = {"${primaryQueue.name}"})
public class RabbitReceiver {

    @Autowired
    private UserRepository userRepository;


//will try several times to insert otherwise it goes to the DLQ
    @RabbitHandler
    public void receive(User user){
            userRepository.save(user);
    }
}
