package aleusers.service;

import aleusers.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class QueueServiceTest {
    @Mock
    private AmqpTemplate template;

    @InjectMocks
    private QueueService queueService;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    // addToQueue(String, Object) - tests
    //calls template.convertAndSend once with parameters given

    @Test
    void receiveShouldCallUserRepositorySaveWithGivenParameter(){
        //given
        String queueName = "A";
        Object message = queueName;
        //when
        queueService.addToQueue(queueName,message);
        //then
        Mockito.verify(template,times(1)).convertAndSend(queueName,message);
    }


}