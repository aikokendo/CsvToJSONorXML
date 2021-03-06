package aleusers.service;


import org.json.CDL;
import org.springframework.stereotype.Component;


@Component
public class ParserJSON implements Parser {
    @Override
    public String parseFromCsv(String csvContent) {
        return CDL.toJSONArray(csvContent).toString();
    }
}
