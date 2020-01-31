package aleusers.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ParserJSONTest {

    @InjectMocks
    private ParserJSON parserJSON;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    //parseFromCsv() - tests
    // 1. given a CSV, generate JSON statement

    @Test
    void parseFromCsvGenerateJSONStatementForCSV() {
        String exampleCsv = "id,name\n1,john\n2,jane";
        String expected = "[{\"name\":\"john\",\"id\":\"1\"},{\"name\":\"jane\",\"id\":\"2\"}]";
        String result = parserJSON.parseFromCsv(exampleCsv);
        Assertions.assertEquals(expected,result);
    }
}