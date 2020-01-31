package aleusers.controller;

import aleusers.converter.UserConverter;
import aleusers.errorhandling.BadRequestException;
import aleusers.model.User;
import aleusers.model.UserDTO;
import aleusers.service.StrategyFinder;
import aleusers.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.*;

import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private StrategyFinder strategyFinder;
    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    //addNewUser - 2 tests
    // 1. is calling userService.createUser once with a user
    // 2. is returning userService.createUser

    @Test
    void addNewUserShouldCallUserServiceCreateUserWithGivenParameter(){
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        User user = new User("John","Doe");
        Mockito.when(userConverter.convertToEntity(any())).thenReturn(user);
        //when
        userController.addNewUser(userDTO);
        //then
        Mockito.verify(userService,times(1)).createUser(user);
    }

    @Test
    void addNewUserShouldReturnUserServiceCreateUserResult(){
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        String expected = "success";
        Mockito.when(userService.createUser(any())).thenReturn(expected);
        //when
        String actual = userController.addNewUser(userDTO);
        //then
        Assertions.assertEquals(expected,actual);
    }

    // getUser(int) - 3 tests
    // 1. executes userService.getUser only once with an int
    // 2. returns the result of userService.getUser if it has a user
    // 2. returns null if userService.getUser is empty.

    @Test
    void getUserShouldCallUserServiceGetUserWithGivenParameters(){
        //given
        int i = 0;
        //when
        userController.getUser(i);
        //then
        Mockito.verify(userService,times(1)).getUser(i);
    }

    @Test
    void getUserShouldReturnUserServiceGetUserResult(){
        //given
        int i = 0;
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        Mockito.when(userService.getUser(anyInt())).thenReturn(Optional.of(user));
        //when
        User actual = userController.getUser(i);
        //then
        Assertions.assertEquals(user,actual);
    }

    @Test
    void getUserShouldReturnUserNullIfUserServiceGetUserIsEmpty(){
        //given
        int i = 0;
        Mockito.when(userService.getUser(anyInt())).thenReturn(Optional.empty());
        //when
        User actual = userController.getUser(i);
        //then
        Assertions.assertNull(actual);
    }

    // getParsedUser(id,query) - tests
    // 1. only using myStrategy.parseFromCsv once with arguments
    // 2. returns the result of myStrategy.parseFromCsv
    // 3. if type is not defined, a BadRequestException should be thrown

    @Test
    void getParsedUserShouldCallMyStrategyParseFromCsvWithGivenParameter(){
        //given
        int id = 1;
        String expected = "A";
        Map<String,String> query = new HashMap<String,String>();
        String type = "T";
        query.put("type",type);
        Mockito.when(userService.getCSVUser(anyInt())).thenReturn(expected);
        //when
        userController.getParsedUser(id,query);
        //then
        Mockito.verify(strategyFinder, times(1)).parseFromCsv(expected ,type);
    }

    @Test
    void getParsedUserShouldReturnMyStrategyParseFromCsvResult(){
        //given
        int id = 1;
        String expected = "A";
        Map<String,String> query = new HashMap<String,String>();
        String type = "T";
        query.put("type",type);
        Mockito.when(strategyFinder.parseFromCsv(any(),any())).thenReturn(expected);
        //when
        String actual = userController.getParsedUser(id,query);
        //then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void getParserUserShouldThrowBadRequestExceptionIfTypeIsNotProvided(){
        //given
        int id = 1;
        Map<String,String> query = new HashMap<String,String>();
        Assertions.assertThrows(BadRequestException.class,
                ()->{
                    userController.getParsedUser(id, query);
                });
        Mockito.verifyNoInteractions(strategyFinder);
    }

    // getAllUsers() - tests
    // 1. calls userService.getAllUsers once
    // 2. return userService.getAllUsers result

    @Test
    void getAllUsersShouldCallUserServiceGetUserWithGivenParameters(){
        //when
        userController.getAllUsers();
        //then
        Mockito.verify(userService,times(1)).getAllUsers();
    }

    @Test
    void getAllUsersShouldReturnUserServiceGetUserResult(){
        //given
        Iterable<User> mockIterator = mock(Iterable.class);
        Mockito.when(userService.getAllUsers()).thenReturn(mockIterator);
        //when
        Iterable<User> actual = userController.getAllUsers();
        //then
        Assertions.assertEquals(mockIterator,actual);
    }

    // getParsedUsers() - tests
    // 1. call strategyFinder.parseFromCsv once
    // 2. return strategyFinder.parseFromCsv result
    // 3. throws BadRequestException if not type provided
    @Test
    void getParsedUsersShouldCallMyStrategyParseFromCsvWithGivenParameter(){
        //given
        String expected = "A";
        Map<String,String> query = new HashMap<String,String>();
        String type = "T";
        query.put("type",type);
        Mockito.when(userService.getCSVUsers()).thenReturn(expected);
        //when
        userController.getParsedUsers(query);
        //then
        Mockito.verify(strategyFinder, times(1)).parseFromCsv(expected ,type);
    }

    @Test
    void getParsedUsersShouldReturnMyStrategyParseFromCsvResult(){
        //given
        String expected = "A";
        Map<String,String> query = new HashMap<String,String>();
        String type = "T";
        query.put("type",type);
        Mockito.when(strategyFinder.parseFromCsv(any(),any())).thenReturn(expected);
        //when
        String actual = userController.getParsedUsers(query);
        //then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void getParserUsersShouldThrowBadRequestExceptionIfTypeIsNotProvided(){
        //given
        Map<String,String> query = new HashMap<String,String>();
        Assertions.assertThrows(BadRequestException.class,
                ()->{
                    userController.getParsedUsers(query);
                });
        Mockito.verifyNoInteractions(strategyFinder);
    }

}