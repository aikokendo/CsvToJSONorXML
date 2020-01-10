package parser;

import org.json.CDL;
import org.json.XML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class parserXML implements Parser {
    @Override
    public String parseFromCsv(String csvContent) {
        return "<root>" + XML.toString(CDL.toJSONArray(csvContent)) + "</root>";
    }
}
