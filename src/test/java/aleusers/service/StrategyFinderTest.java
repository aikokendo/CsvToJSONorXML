package aleusers.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class StrategyFinderTest {
    @Mock
    private ParserJSON myParserJSON;
    @Mock
    private ParserXML myParserXML;
    @Mock
    private ParserINSERT myParserINSERT;
    @Mock
    private ParserPIPE myParserPIPE;
    @InjectMocks
    private StrategyFinder strategyFinder;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    // parseFromCsv(String, String) - tests
    // Instances ParserJSON when sending type JSON
    // Instances ParserXML when sending type XML
    // Instances ParserINSERT when sending type INSERT
    // Instances ParserPIPE as a default type
    // returns parser.parseFromCsv result

    @Test
    void parseFromCsvShouldInstanceParseJSONIfTypeJSONIsGiven(){
        //given
        String csvText = "A";
        String output = "JSON";
        //when
        strategyFinder.parseFromCsv(csvText,output);
        //then
        Mockito.verify(myParserJSON,Mockito.times(1)).parseFromCsv(csvText);
        Mockito.verifyNoInteractions(myParserINSERT);
        Mockito.verifyNoInteractions(myParserPIPE);
        Mockito.verifyNoInteractions(myParserXML);
    }

    @Test
    void parseFromCsvShouldInstanceParseINSERTIfTypeINSERTIsGiven(){
        //given
        String csvText = "A";
        String output = "INSERT";
        //when
        strategyFinder.parseFromCsv(csvText,output);
        //then
        Mockito.verify(myParserINSERT,Mockito.times(1)).parseFromCsv(csvText);
        Mockito.verifyNoInteractions(myParserXML);
        Mockito.verifyNoInteractions(myParserPIPE);
        Mockito.verifyNoInteractions(myParserJSON);
    }

    @Test
    void parseFromCsvShouldInstanceParseXMLIfTypeXMLIsGiven(){
        //given
        String csvText = "A";
        String output = "XML";
        //when
        strategyFinder.parseFromCsv(csvText,output);
        //then
        Mockito.verify(myParserXML,Mockito.times(1)).parseFromCsv(csvText);
        Mockito.verifyNoInteractions(myParserINSERT);
        Mockito.verifyNoInteractions(myParserPIPE);
        Mockito.verifyNoInteractions(myParserJSON);
    }

    @Test
    void parseFromCsvShouldInstanceParsePIPEIfTypePIPEIsGiven(){
        //given
        String csvText = "A";
        String output = "";
        //when
        strategyFinder.parseFromCsv(csvText,output);
        //then
        Mockito.verify(myParserPIPE,Mockito.times(1)).parseFromCsv(csvText);
        Mockito.verifyNoInteractions(myParserINSERT);
        Mockito.verifyNoInteractions(myParserXML);
        Mockito.verifyNoInteractions(myParserJSON);
    }



//
//    public String parseFromCsv(String csvText, String output){
//        Parser myParser;
//        if (output.equals("JSON")){
//            myParser = myParserJSON;
//        }

}