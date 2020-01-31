package aleusers.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ParserXMLTest {

    @InjectMocks
    private ParserXML parserXML;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    //parseFromCsv() - tests
    // 1. given a CSV, generate XML statement

    @Test
    void parseFromCsvGenerateJSONStatementForCSV() {
        String exampleCsv = "id,name\n1,john\n2,jane";
        String expected = "<root><array><name>john</name><id>1</id></array><array><name>jane</name><id>2</id></array></root>";
        String result = parserXML.parseFromCsv(exampleCsv);
        Assertions.assertEquals(expected,result);
    }

}