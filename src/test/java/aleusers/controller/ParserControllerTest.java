package aleusers.controller;

import aleusers.errorhandling.BadRequestException;
import aleusers.service.StrategyFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

class ParserControllerTest {

    @Mock
    private StrategyFinder strategyFinder;

    @InjectMocks
    private ParserController parserController;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    //getParse - 3 required tests:
    // getParse is calling strategyFinder.parseFromCsv once with right parameters
    // getParse returns the result from strategyFinder.parseFromCsv
    // not sending a "type" parameter causes an exception.
    // not sending examplecsv causes an exception.

    @Test
    void getParserShouldCallStrategyFinderParseFromCsvWithGivenParameter(){
        //given
        String examplecsv = "A";
        Map<String,String> query = new HashMap<String,String>();
        String type = "T";
        query.put("type",type);
        //when
        parserController.getParser(query,examplecsv);

        //then
        Mockito.verify(strategyFinder, times(1)).parseFromCsv(examplecsv,type);
    }

    @Test
    void getParserShouldReturnStrategyFinderParseFromCsvResult(){
        //given
        String expected = "A";
        Mockito.when(strategyFinder.parseFromCsv(any(),any())).thenReturn(expected);
        Map<String,String> query = new HashMap<String,String>();
        String type = "T";
        query.put("type",type);
        //when
        String actual = parserController.getParser(query,expected);
        //then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void getParserShouldThrowBadRequestExceptionIfTypeIsNotProvided(){
        //given
        String expected = "A";
        Map<String,String> query = new HashMap<String,String>();
        Assertions.assertThrows(BadRequestException.class,
                ()->{
                    parserController.getParser(query,expected);
                });
        Mockito.verifyNoInteractions(strategyFinder);
    }

    @Test
    void getParserShouldThrowBadRequestExceptionIfCsvTextIsNotProvided(){
        //given
        String expected = null;
        Map<String,String> query = new HashMap<String,String>();
        String type = "T";
        query.put("type",type);
        Assertions.assertThrows(BadRequestException.class,
                ()->{
                    parserController.getParser(query,expected);
                });
        Mockito.verifyNoInteractions(strategyFinder);
    }
}