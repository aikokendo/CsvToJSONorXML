package aleusers.service;

import org.json.CDL;
import org.json.XML;
import org.springframework.stereotype.Component;


@Component
public class ParserXML implements Parser {
    @Override
    public String parseFromCsv(String csvContent) {
        return "<root>" + XML.toString(CDL.toJSONArray(csvContent)) + "</root>";
    }
}
