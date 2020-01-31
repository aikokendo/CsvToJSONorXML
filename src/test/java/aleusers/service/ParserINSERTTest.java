package aleusers.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


class ParserINSERTTest {

    @InjectMocks
    private ParserINSERT parserINSERT;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    //parseFromCsv() - tests
    // 1. given a CSV, generate INSERT statement
    // 2. if no actual data rows are present, it should return an empty string

    @Test
    void parseFromCsvGenerateInsertStatementForCSV() {
        String exampleCsv = "id,name\n1,john\n2,jane";
        String expected = "INSERT INTO TABLE (id,name) VALUES (1,john);\n" +
                "INSERT INTO TABLE (id,name) VALUES (2,jane);";
        String result = parserINSERT.parseFromCsv(exampleCsv);
        Assertions.assertEquals(expected,result);
    }


    @Test
    void parseFromCsvReturnsEmptyStringIfNoDataRowsAreProvided() {
        String exampleCsv = "id,name";
        String expected = "";
        String result = parserINSERT.parseFromCsv(exampleCsv);
        Assertions.assertEquals(expected,result);
    }
}