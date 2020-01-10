package parser;


import org.json.CDL;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class parserJSON implements Parser {
    @Override
    public String parseFromCsv(String csvContent) {
        return CDL.toJSONArray(csvContent).toString();
    }
}
