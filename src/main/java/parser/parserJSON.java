package parser;


import org.json.CDL;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class parserJSON implements Parser {
    @Override
    public String parseFromCsvFile(String csvFileName) {
        String contents = "";
        try {
            contents = Files.lines(Paths.get(csvFileName)).collect(Collectors.joining("\n"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return CDL.toJSONArray(contents).toString();
    }
}
