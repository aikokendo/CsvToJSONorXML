package aleusers.service;

import aleusers.model.User;
import aleusers.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private QueueService queueService;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        userService.primaryQueueName = "QueueTestName";
    }

    //getUser(int) - tests
    // calls userRepository.existsById once with parameters
    // calls userRepository.findById once with parameters
    // return userRepository.findById(int) results
    // if user does not exists, throw an illegal argument exception.
    @Test
    void getUserShouldCallUserRepositoryExistsByIdWithGivenParameters(){
        //given
        int id = 1;
        User user = new User();
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.existsById(anyInt())).thenReturn(true);
        //when
        userService.getUser(id);
        //then
        Mockito.verify(userRepository, times(1)).existsById(id);
    }

    @Test
    void getUserShouldCallUserRepositoryFindByIdWithGivenParameters(){
        //given
        int id = 1;
        User user = new User();
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.existsById(anyInt())).thenReturn(true);
        //when
        userService.getUser(id);
        //then
        Mockito.verify(userRepository, times(1)).findById(id);
    }

    @Test
    void getUserShouldReturnUserRepositoryFindByIdResult(){
        //given
        int id = 1;
        User user = new User("john","doe");
        Optional<User> opUser = Optional.of(user);
        Mockito.when(userRepository.findById(anyInt())).thenReturn(opUser);
        Mockito.when(userRepository.existsById(anyInt())).thenReturn(true);
        //when
        Optional<User> actual = userService.getUser(id);
        //then
        Assertions.assertEquals(opUser,actual);
    }

    @Test
    void getUserShouldIllegalArgumentExceptionIfUserDoesNotExists(){
        //given
        int id = 1;
        Mockito.when(userRepository.existsById(anyInt())).thenReturn(false);
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->{
                    userService.getUser(id);
                });
    }

    //createUser(user) - Tests
    // calls queueService.addToQueue() once with parameters
    // returns "success" as a result.
    @Test
    void createUserCallsQueueServiceAddToQueueWithGivenParameters(){
        String queueName = "QueueTestName";
        User user = new User("John","Doe");
        userService.createUser(user);
        Mockito.verify(queueService,times(1)).addToQueue(queueName,user);
    }

    @Test
    void createUserShouldReturnSuccessMessage(){
        String expected = "Success";
        User user = new User("John","Doe");
        String actual = userService.createUser(user);
        Assertions.assertEquals(expected,actual);
    }

    // getAllUser() - tests
    // check it calls userRepository.findAll once
    // check it returns the result for userRepository.findAll

    @Test
    void getAllUsersShouldCallUserRepositoryFindAll(){
        userService.getAllUsers();
        Mockito.verify(userRepository,times(1)).findAll();
    }

    @Test
    void getAllUsersShouldReturnUserRepositoryFindAllResult(){
        Iterable<User> mockIterator = mock(Iterable.class);
        Mockito.when(userService.getAllUsers()).thenReturn(mockIterator);
        //when
        Iterable<User> actual = userService.getAllUsers();
        //then
        Assertions.assertEquals(mockIterator,actual);
    }

    // getCSVUser(id) - tests
    // Return CSV format string for a user
    // Return "user not found" if there is no user for id

    @Test
    void getCSVUserReturnsCSVFormatForUser(){
        int id = 1;
        User user = new User("john","doe");
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.existsById(anyInt())).thenReturn(true);
        String expected = "'id','first_name','last_name'\n0,'john','doe'";
        //when
        String actual = userService.getCSVUser(1);
        //then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void getCSVUserReturnsErrorMessageIfUserNotFound(){
        int id = 1;
        User user = new User("john","doe");
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        Mockito.when(userRepository.existsById(anyInt())).thenReturn(true);
        String expected = "user not found";
        //when
        String actual = userService.getCSVUser(1);
        //then
        Assertions.assertEquals(expected,actual);
    }


    // getCsvUsers() - tests
    // returns CSV format of all users found
    // returns empty string if no users

    @Test
    void getCsvUsersShouldReturnCSVFormatOfAllUsers(){
        //given
        String expected = "'id','first_name','last_name'\n0,'john','doe'";
        Iterable<User> users = new Iterable<User>() {
            @Override
            public Iterator<User> iterator() {
                User user = new User("john","doe");
                ArrayList<User> users = new ArrayList<>();
                users.add(user);
                return users.iterator();
            }
        };
        Mockito.when(userService.getAllUsers()).thenReturn(users);
        //when
        String actual = userService.getCSVUsers();
        //then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void getCsvUsersShouldReturnEmptyStringIfNoUsers(){
        //given
        String expected = "";
        Iterable<User> users = new Iterable<User>() {
            @Override
            public Iterator<User> iterator() {
                ArrayList<User> users = new ArrayList<>();
                return users.iterator();
            }
        };
        Mockito.when(userService.getAllUsers()).thenReturn(users);
        //when
        String actual = userService.getCSVUsers();
        //then
        Assertions.assertEquals(expected,actual);
    }
}