package aleusers.consumer;

import aleusers.model.User;
import aleusers.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class RabbitReceiverTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RabbitReceiver rabbitReceiver;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


// receive(user) - tests
    //1. calls userRepository.save once with the parameters

    @Test
    void receiveShouldCallUserRepositorySaveWithGivenParameter(){
        //given
        User user = new User("John","Doe");
        //when
        rabbitReceiver.receive(user);
        //then
        Mockito.verify(userRepository,times(1)).save(user);
    }

}