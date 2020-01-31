package aleusers.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ParserPIPETest {

    @InjectMocks
    private ParserPIPE parserPIPE;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    //parseFromCsv() - tests
    // 1. given a CSV, generate PIPE statement

    @Test
    void parseFromCsvGenerateJSONStatementForCSV() {
        String exampleCsv = "id,name\n1,john\n2,jane";
        String expected = "id|name\n1|john\n2|jane";
        String result = parserPIPE.parseFromCsv(exampleCsv);
        Assertions.assertEquals(expected,result);
    }
}