package parser;

import org.json.CDL;
import org.json.XML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class parserXML implements Parser {
    @Override
    public String parseFromCsvFile(String csvFileName) {
        String contents = "";
        String csvJustName = csvFileName.split("\\.")[0];
        try {
            contents = Files.lines(Paths.get(csvFileName)).collect(Collectors.joining("\n"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return "<"+ csvJustName+">" + XML.toString(CDL.toJSONArray(contents)) + "</"+csvJustName+">";
    }
}
